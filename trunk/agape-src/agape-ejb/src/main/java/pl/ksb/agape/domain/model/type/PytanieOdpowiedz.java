package pl.ksb.agape.domain.model.type;

import pl.ksb.agape.domain.model.Odpowiedz;
import pl.ksb.agape.domain.model.Pytanie;

public class PytanieOdpowiedz {

	private Pytanie pytanie;
	private Odpowiedz odpowiedz;

	public PytanieOdpowiedz(Pytanie pytanie, Odpowiedz odpowiedz) {
		this.pytanie = pytanie;
		this.odpowiedz = odpowiedz;
	}

	public PytanieOdpowiedz() {
	}

	public Pytanie getPytanie() {
		return pytanie;
	}

	public void setPytanie(Pytanie pytanie) {
		this.pytanie = pytanie;
	}

	public Odpowiedz getOdpowiedz() {
		return odpowiedz;
	}

	public void setOdpowiedz(Odpowiedz odpowiedz) {
		this.odpowiedz = odpowiedz;
	}

}
