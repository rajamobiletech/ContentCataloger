package com.cisco.ccs.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NResourceHandler {
	public static ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle("com.cisco.ccs.i18n.ccs_i18n",
				locale);
	}

	public static ResourceBundle getResourceBundle() {
		Locale locale = new Locale("en", "US");
		return ResourceBundle.getBundle(
				"com.cisco.ccs.i18n.ccs_i18n_en_US", locale);
	}

	public static ResourceBundle getLetterResourceBundle(Locale locale) {
		return ResourceBundle.getBundle(
				"com.cisco.ccs.i18n.ccs_i18n_letters", locale);
	}

	public static ResourceBundle getLetterResourceBundle() {
		Locale locale = new Locale("en", "US");
		return ResourceBundle.getBundle(
				"com.cisco.ccs.i18n.ccs_i18n_letters", locale);
	}
}
