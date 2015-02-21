package pl.ksb.agape.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pl.ksb.agape.domain.dao.ApplicationTextDAOBean;
import pl.ksb.agape.domain.model.Lesson;
import pl.ksb.agape.domain.model.User;

@Stateless
@LocalBean
public class ChangeInLessonMailSender {

	@EJB
	private MailSenderService mailSenderService;

	@EJB
	private ApplicationTextDAOBean applicationTextDAOBean;

	public void sendMailToStudentWithShareLessonInfo(User student,
			User teacher, Lesson lesson) {
		String subject = applicationTextDAOBean.getText(
				"mail.udostepnienie.lekcji.temat").getText();
		String message = applicationTextDAOBean.getText(
				"mail.udostepnienie.lekcji.wiadomosc").getText();
		send(student, subject, message);

	}

	public void sendMailToStudentWithCheckLessonInfo(User student,
			User teacher, Lesson lesson) {
		String subject = applicationTextDAOBean.getText(
				"mail.sprawdzenie.lekcji.temat").getText();
		String message = applicationTextDAOBean.getText(
				"mail.sprawdzenie.lekcji.wiadomosc").getText();
		send(student, subject, message);

	}

	private void send(User user, String subject, String message) {
		if (Boolean.TRUE.equals(user.getSendMail())) {
			mailSenderService.send(user.getEmail(), subject, message);
		}

	}

	public void sendMailToTeacherWithSendLessonInfo(User student, User teacher,
			Lesson lesson) {
		String subject = applicationTextDAOBean.getText(
				"mail.wyslanie.lekcji.temat").getText();
		String message = applicationTextDAOBean.getText(
				"mail.wyslanie.lekcji.wiadomosc").getText();
		send(teacher, subject, message);

	}

}
