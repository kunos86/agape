package pl.ksb.agape.view.bean.datamodel;

import java.util.List;

import java.util.ListIterator;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;

import org.ajax4jsf.model.ExtendedDataModel;

import org.ajax4jsf.model.Range;

import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;



 

public abstract class PaginatingDataModel<T> extends ExtendedDataModel<T> implements Arrangeable {

 

    private SequenceRange cachedRange = null;

    private Integer cachedRowCount = null;

    private List<T> cachedList = null;

    private Object rowKey;

    private List<FilterField> filterFields;
    private List<SortField> sortFields;
 

    public abstract List<T> getDataList(int firstRow, int numRows);

    public abstract Object getKey(T t);

    public abstract int getTotalCount();

 

    public PaginatingDataModel() {

        super(); // shouldn't be needed try removing

    }

 

    @Override

    public void walk(FacesContext ctx, DataVisitor dv, Range range, Object argument) {

 

        SequenceRange sr = (SequenceRange) range;

 

        if (cachedList == null || !equalRanges(cachedRange, sr)) {

 

            int firstRow = sr.getFirstRow();

            int numRows = sr.getRows();

 

            //Log.log("firstRow: " + firstRow + " numRows: " + numRows);

 

            cachedList = getDataList(firstRow, numRows);

            cachedRange = sr;

        }

 

        for (T t : cachedList) {

            if (getKey(t) == null) {

 

                /*

                 * the 2nd param is used to build the client id of the table

                 * row, i.e. mytable:234:inputname, so don't let it be null.

                 */

 

                throw new IllegalStateException("found null key");

            }

            dv.process(ctx, getKey(t), argument);

        }

    }

 

    /*

     * The rowKey is the id from getKey, presumably obtained from

     * dv.process(...).

     */

    @Override

    public void setRowKey(Object rowKey) {

        this.rowKey = rowKey;

    }

 

    @Override

    public Object getRowKey() {

        return rowKey;

    }

 

    @Override

    public boolean isRowAvailable() {

        return (getRowData() != null);

    }

 

    @Override

    public int getRowCount() {

        if (cachedRowCount == null) {

            cachedRowCount = getTotalCount();

        }

        return cachedRowCount;

    }

 

    @Override

    public T getRowData() {

        for (T t : cachedList) {

            if (getKey(t).equals(this.getRowKey())) {

                return t;

            }

        }

        return null;

    }

 

    protected static boolean equalRanges(SequenceRange range1, SequenceRange range2) {

        if (range1 == null || range2 == null) {

            return range1 == null && range2 == null;

        } else {

            return range1.getFirstRow() == range2.getFirstRow()

                    && range1.getRows() == range2.getRows();

        }

    }

 

    /*

     * get/setRowIndex are used when doing multiple select in an

     * extendedDataTable, apparently. Not tested. Actually, the get method is

     * used when using iterationStatusVar="it" & #{it.index}.

     */

 

    @Override

    public int getRowIndex() {

 

        if (cachedList != null) {

            ListIterator<T> it = cachedList.listIterator();

            while (it.hasNext()) {

                T t = it.next();

                if (getKey(t).equals(this.getRowKey())) {

                    return it.previousIndex() + cachedRange.getFirstRow();

                }

            }

        }

 

        return -1;

    }

 

    @Override

    public void setRowIndex(int rowIndex) {

 

        int upperBound = cachedRange.getFirstRow() + cachedRange.getRows();

 

        if (rowIndex >= cachedRange.getFirstRow() && rowIndex < upperBound) {

            int index = rowIndex % cachedRange.getRows();

            T t = cachedList.get(index);

            setRowKey(getKey(t));

        }

    }

 

    @Override

    public Object getWrappedData() {

        throw new UnsupportedOperationException("Not supported yet.");

    }

 

    @Override

    public void setWrappedData(Object data) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

 

    public List<T> getCachedList() {

        return cachedList;

    }
    
	@Override
	public void arrange(FacesContext arg0, ArrangeableState state) {
		
		if (state != null) {
			this.filterFields = state.getFilterFields();
			this.sortFields = state.getSortFields();
			if (sortFields!=null){
				while(sortFields.iterator().hasNext()){
					SortField s = sortFields.iterator().next();
					System.out.println(s);
				}
			}
			this.cachedRange = null;
			this.cachedRowCount=null;
			}	
	}

}