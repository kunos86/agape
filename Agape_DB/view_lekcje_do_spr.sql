CREATE OR REPLACE VIEW AGAPE.LEKCJE_DO_SPR AS
SELECT 
st.id
, st.data_wyslania
, st.data_udostepnienia
, st.id_lekcja
, l.nr_lekcji
, l.id_kursu
, k.nr_kursu
, st.id_osoba
, os.imie
, os.nazwisko
, os.email
, un.id_nauczyciela
  FROM agape.stan_zaawansowania st, agape.osoba os, agape.lekcja l,agape.kurs k, agape.uczniowie_naucz un

WHERE 
st.data_sprawdzenia is null
AND st.data_wyslania is not null
AND st.id_osoba = os.id
AND st.id_lekcja = l.id
AND os.id = un.id_ucznia
AND os.status = 'AKTUALNY'
AND k.id = l.id_kursu
AND un.aktualny = 'Y';

ALTER TABLE agape.lekcje_do_spr
  OWNER TO agape;