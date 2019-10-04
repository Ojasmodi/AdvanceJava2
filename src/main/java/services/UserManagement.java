package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.User;

public class UserManagement {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("User_details");
	static EntityManager em = emf.createEntityManager();

	// method to verify whether user is present or not by username and password
	public boolean isUserExists(String username) throws Exception {
		em.getTransaction().begin();
		User retrievedUser = em.find(User.class, username);
		if (retrievedUser != null) {
			em.getTransaction().commit();
			return true;
		} else {
			em.getTransaction().commit();
			return false;
		}
	}

	// method to verify whether user is present or not by username
	public boolean isUserExists(String username, String password) throws Exception {
		em.getTransaction().begin();
		User retrievedUser = em.find(User.class, username);
		if (retrievedUser != null) {
			if (retrievedUser.getPassword().equals(password)) {
				em.getTransaction().commit();
				return true;
			} else {
				em.getTransaction().commit();
				return false;
			}
		} else {
			em.getTransaction().commit();
			return false;
		}
	}

	// method to verify user's secret question and answer
	public boolean isUserQuestionAndAnswerRight(String username, String question, String answer) throws Exception {
		em.getTransaction().begin();
		User retrievedUser = em.find(User.class, username);
		if (retrievedUser != null) {
			if (retrievedUser.getSecretQuestion().equals(question) && retrievedUser.getSecretAnswer().equals(answer)) {
				em.getTransaction().commit();
				return true;
			} else {
				em.getTransaction().commit();
				return false;
			}
		} else {
			em.getTransaction().commit();
			return false;
		}
	}

	// method to reset password of user
	public boolean resetPassword(String username, String password) throws Exception {
		em.getTransaction().begin();
		User retrievedUser = em.find(User.class, username);
		if (retrievedUser != null) {
			retrievedUser.setPassword(password);
			em.getTransaction().commit();
			return true;
		} else {
			em.getTransaction().commit();
			return false;
		}
	}
}
