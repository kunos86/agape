package pl.ksb.agape.view.datamodel;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.Expression;
import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.richfaces.model.ExtendedFilterField;
import org.richfaces.model.FilterField;
import org.richfaces.model.Modifiable;
import org.richfaces.model.Ordering;
import org.richfaces.model.SortField2;

import pl.ksb.agape.tools.reflect.EntityField;
import pl.ksb.agape.tools.reflect.ReflectTools;

public abstract class PaginatingDataModel<T, U> extends SerializableDataModel
		implements Modifiable {

	private static final long serialVersionUID = 2954923950179861809L;

	protected U currentPk;

	protected int rowIndex;

	protected boolean descending = false;

	protected String sortField;

	protected HashMap<String, Object> filterMap = new HashMap<String, Object>();

	protected boolean detached;

	protected List<U> wrappedKeys = new ArrayList<U>();

	protected Integer rowCount;

	private Map<String, EntityField> reflectFields = null;

	protected Map<U, T> wrappedData = new HashMap<U, T>();

	protected Criteria convertFilters(HashMap<String, Object> filterMap,
			Criteria criteria) {

		for (String key : filterMap.keySet()) {

			// criteria.add(Restrictions.like(key, filterMap.get(key))
			// .ignoreCase());

			String tmpKey = key;
			if (getReflectFields().get(key) != null) {
				tmpKey = getReflectFields().get(key).getDbName();
			}

			criteria.add(Restrictions.sqlRestriction("upper(cast( " + tmpKey
					+ " as text)) like '"
					+ filterMap.get(key).toString().toUpperCase() + "%'"));
		}

		return criteria;

	}

	protected Criteria convertSortValue(String sortField, boolean descending,
			Criteria criteria) {

		return descending ? criteria.addOrder(Order.desc(sortField)) : criteria
				.addOrder(Order.asc(sortField));

	}

	abstract public Criteria createCriteriaQuery();

	/**
	 * 
	 * @param firstRow
	 * 
	 * @param numberOfRows
	 * 
	 * @param sortField
	 * 
	 * @param descending
	 * 
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findObjects(int firstRow, int numberOfRows,
			String sortField, HashMap<String, Object> filterMap,
			boolean descending) {
		Criteria criteria = this.createCriteriaQuery();
		criteria = this.convertFilters(filterMap, criteria);

		if (sortField != null) {
			criteria = this.convertSortValue(sortField, descending, criteria);
		}

		criteria.setFirstResult(firstRow);
		criteria.setMaxResults(numberOfRows);

		return criteria.list();
	}

	/**
	 * @param object
	 * 
	 * @return U
	 */
	public abstract U getId(T object);

	/**
	 * 
	 * @return Long
	 */
	public Number getNumRecords(HashMap<String, Object> filterMap) {
		Criteria criteria = this.createCriteriaQuery();

		criteria = this.convertFilters(filterMap, criteria);

		criteria.setProjection(Projections.rowCount());

		return (Number) criteria.uniqueResult();
	}

	/**
	 * 
	 * @param id
	 * 
	 * @return T
	 */
	public abstract T getObjectById(U id);

	/**
	 * 
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if (this.rowCount == null) {
			this.rowCount = this.getNumRecords(this.filterMap).intValue();
		}
		return this.rowCount;
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	@Override
	public Object getRowData() {
		if (this.currentPk == null) {
			return null;
		}
		T object = this.wrappedData.get(this.currentPk);
		if (object == null) {
			object = this.getObjectById(this.currentPk);
			this.wrappedData.put(this.currentPk, object);
		}
		return object;
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#getRowIndex()
	 */
	@Override
	public int getRowIndex() {
		return this.rowIndex;
	}

	/**
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {
		return this.currentPk;
	}

	/**
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#getSerializableModel(org.ajax4jsf.model.Range)
	 */
	@Override
	public SerializableDataModel getSerializableModel(final Range range) {
		if (this.wrappedKeys != null) {
			this.detached = true;
			return this;
		}
		return null;
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#getWrappedData()
	 */
	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#isRowAvailable()
	 */

	@Override
	public boolean isRowAvailable() {
		if (this.currentPk == null) {
			return false;
		}
		if (this.wrappedKeys.contains(this.currentPk)) {
			return true;
		}
		if (this.wrappedData.entrySet().contains(this.currentPk)) {
			return true;
		}
		try {
			if (this.getObjectById(this.currentPk) != null) {
				return true;
			}
		} catch (final Exception e) {
		}
		return false;
	}

	// @Override
	public void modify(List<FilterField> filterFields,
			List<SortField2> sortFields) {
		this.filterMap.clear();
		SortField2 sortField2 = null;
		String expressionStr = null;
		ExtendedFilterField extendedFilterField = null;
		Expression expression = null;
		String value = null;
		if (sortFields != null && !sortFields.isEmpty()) {
			sortField2 = sortFields.get(0);
			expression = sortField2.getExpression();
			expressionStr = expression.getExpressionString();
			if (!expression.isLiteralText()) {
				expressionStr = expressionStr.replaceAll("[#|$]{1}\\{.*?\\.",
						"").replaceAll("\\}", "");
			}
			this.sortField = expressionStr;
			if (sortField2.getOrdering() == Ordering.DESCENDING) {
				this.descending = true;
			} else {
				this.descending = false;
			}
		}
		if (filterFields != null && !filterFields.isEmpty()) {
			for (FilterField filterField : filterFields) {
				if (filterField instanceof ExtendedFilterField) {
					extendedFilterField = (ExtendedFilterField) filterField;
					value = extendedFilterField.getFilterValue();
					if (value != null && !value.equals("")) {
						expression = extendedFilterField.getExpression();
						expressionStr = expression.getExpressionString();
						if (!expression.isLiteralText()) {
							expressionStr = expressionStr.replaceAll(
									"[#|$]{1}\\{.*?\\.", "").replaceAll("\\}",
									"");
						}
						this.filterMap.put(expressionStr, value);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#setRowIndex(int)
	 */
	@Override
	public void setRowIndex(final int arg0) {
		this.rowIndex = arg0;
	}

	/**
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void setRowKey(final Object key) {
		this.currentPk = (U) key;
	}

	/**
	 * 
	 * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
	 */
	@Override
	public void setWrappedData(final Object data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @see org.ajax4jsf.model.SerializableDataModel#update()
	 */
	@Override
	public void update() {
		this.detached = false;
	}

	protected String getSortField() {
		return sortField;
	}

	public Map<String, EntityField> getReflectFields() {
		if (reflectFields == null) {
			reflectFields = ReflectTools
					.getEntityFileds(getTypeParameterClass());
		}
		return reflectFields;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getTypeParameterClass() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<T>) paramType.getActualTypeArguments()[0];
	}

	/**
	 * 
	 * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext,
	 *      org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range,
	 *      java.lang.Object)
	 */
	@Override
	public void walk(final FacesContext context, final DataVisitor visitor,
			final Range range, final Object argument) throws IOException {
		final int firstRow = ((SequenceRange) range).getFirstRow();
		final int numberOfRows = ((SequenceRange) range).getRows();
		if (this.detached) {
			for (final U key : this.wrappedKeys) {
				this.setRowKey(key);
				visitor.process(context, key, argument);
			}
		} else { // if not serialized, than we request data from data provider
			this.wrappedKeys = new ArrayList<U>();
			for (final T object : this.findObjects(firstRow, numberOfRows,
					getSortField(), this.filterMap, this.descending)) {
				this.wrappedKeys.add(this.getId(object));
				this.wrappedData.put(this.getId(object), object);
				visitor.process(context, this.getId(object), argument);
			}
		}
	}
}
