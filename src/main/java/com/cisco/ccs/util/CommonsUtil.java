package com.cisco.ccs.util;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.security.Key;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.xml.bind.DatatypeConverter;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Marshaller;
//
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.owasp.esapi.ESAPI;
//import org.owasp.esapi.Encoder;
//import org.owasp.esapi.codecs.Codec;
//import org.owasp.esapi.codecs.MySQLCodec;
//import org.owasp.esapi.codecs.MySQLCodec.Mode;
//
//import com.cisco.ccs.exception.InvalidPLEPropertyException;
//import com.cisco.ccs.holder.SkuUpdateFlagHolder;
import com.cisco.ccs.i18n.I18NResourceHandler;
//import com.cisco.ccs.model.content.Course;
//import com.cisco.ccs.model.order.OrderResponse;
//import com.cisco.ccs.model.search.SearchCriteria;
//import com.cisco.ccs.model.skumanagement.sku.DkitMaster;
//import com.cisco.ccs.model.usermanagement.CCOUser;
//import com.cisco.ccs.service.PLEServiceConstants;

public final class CommonsUtil {

    private static final String EMPTY_STRING = "";
    public static final String DATE_FORMAT_MM_dd_yyyy = "MM-dd-yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "MM-dd-yyyy HH:mm z";
    // private static final String DATE_TIME_FORMAT_FEEDBACK =
    // "yyyy-MM-dd HH:mm:ss";
    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
    private static Locale locale = new Locale("en", "US");
    private static final String PLE_NONCE_CACHE = "pleNonceCache";
    private static final String PLE_PRINCIPAL_CACHE = "roleStatusUpdatedCache";

    private static final String timeZone = PLECommonsUtil.getMessage("Timezone");

//    private CommonsUtil() {
//        throw new AssertionError();
//    }
//
//    /**
//     * Method: JSONObject convertBeantoJSON(Object bean) Description: Convert
//     * bean to json.
//     * 
//     * @param bean
//     *            : the bean
//     * @return the jSON object
//     */
//    public static final JSONObject convertBeantoJSON(Object bean) {
//        JSONObject json = new JSONObject();
//        try {
//            for (Method m : bean.getClass().getMethods()) {
//                String name = m.getName();
//                if (name.indexOf("get") >= 0) {
//                    String propName = name.split("get")[1];
//                    propName = propName.replace(propName.charAt(0), Character.toLowerCase(propName.charAt(0)));
//                    if (!propName.equals("class"))
//                        json.put(propName, m.invoke(bean, null));
//                }
//            }
//        } catch (Exception e) {
//        }
//        return json;
//    }
//
//    /**
//     * Method: String getRealPath(HttpServletRequest actionRequest) Description:
//     * Gets the real path.
//     * 
//     * @param actionRequest
//     *            : the action request
//     * @return the real path
//     */
//    public static final String getRealPath(HttpServletRequest actionRequest) {
//        HttpSession session = actionRequest.getSession();
//        ServletContext ctx = session.getServletContext();
//        String path = ctx.getRealPath("/");
//        return path;
//    }
//
//    /**
//     * Method: String getFormattedString(String value) Description: Returns
//     * empty string if the passed value is null else it formats the value by
//     * removing all the spaces and returns back the same string.
//     * 
//     * @param value
//     *            : the value
//     * @return the formatted string
//     */
//    public static final String getFormattedString(final String value) {
//        return (value == null) ? EMPTY_STRING : value.trim();
//    }
//
//    /**
//     * Method: int getFormattedInt(String value) Description: Formats the String
//     * value to int, if it is unable to do so then returns 0s.
//     * 
//     * @param value
//     *            : the value
//     * @return the formatted int
//     */
//    public static final int getFormattedInt(final String value) {
//        int result;
//        try {
//            result = Integer.parseInt(value);
//        } catch (NumberFormatException e) {
//            result = 0;
//        }
//        return result;
//    }

    /**
     * Checks if the passed String is null or empty.
     * 
     * @param value
     *            the value
     * @return true, if is null or empty
     */
    public static final boolean isNullOrEmpty(String value) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static final boolean isNull(Object value) {
        if (value == null) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed List is null or empty.
     * 
     * @param value
     *            the value
     * @return true, if is null or empty
     */
    public static final boolean isNullOrEmpty(List value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed Map is null or empty.
     * 
     * @param value
     *            the value
     * @return true, if is null or empty
     */
    public static final boolean isNullOrEmpty(Map value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed Date is null or empty.
     * 
     * @param value
     *            the value
     * @return true, if is null or empty
     */
    public static final boolean isNullOrEmpty(Date value) {
        if (value == null) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed array is null or empty.
     * 
     * @param array
     *            the array
     * @return true, if is null or empty
     */
    public static final boolean isNullOrEmpty(String[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }

//    /**
//     * Method: Date getDateFromPattern(String pattern, String dateString)
//     * Description: Converts valid date string to Date object in a specific
//     * format that is passed.
//     * 
//     * @param pattern
//     *            : the pattern
//     * @param dateString
//     *            : the date string
//     * @return the date from pattern
//     * @throws ParseException
//     *             : the parse exception
//     */
//    public static final Date getDateFromPattern(String pattern, String dateString) throws ParseException {
//        Date date = null;
//        if (!isNullOrEmpty(pattern) && !isNullOrEmpty(dateString)) {
//            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
//            date = format.parse(dateString);
//        }
//        return date;
//    }
//
//    /**
//     * Method: String getCurrentDateTime() Description: Gets the current date
//     * time.
//     * 
//     * @return the current date time
//     */
//    public static final String getCurrentDateTime() {
//        Calendar cal = Calendar.getInstance();
//        Date date = cal.getTime();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
//        return dateFormat.format(date);
//    }
//
//    /**
//     * Method: String getCurrentDate() Description: Gets the current date in
//     * String format.
//     * 
//     * @return the current date
//     */
//    public static final String getCurrentDate() {
//        DateFormat format = new SimpleDateFormat(DATE_FORMAT_MM_dd_yyyy);
//        return format.format(new Date());
//    }

    /**
     * Gets the formatted date from pattern.
     * 
     * @param date
     *            the date
     * @param pattern
     *            the pattern
     * @return the formatted date from pattern
     * @throws ParseException
     *             the parse exception
     */
    public static final String getFormattedDateFromPattern(Date date, String pattern) throws ParseException {
        String dateStr = "";
        if (!isNullOrEmpty(date)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(TimeZone.getTimeZone(timeZone));
            dateStr = format.format(date);

        }
        return dateStr;
    }

//    /**
//     * Gets the formatted date.
//     * 
//     * @param date
//     *            the date
//     * @return the formatted date
//     * @throws ParseException
//     *             the parse exception
//     */
//    public static final String getFormattedDate(Date date) throws ParseException {
//        String pattern = DATE_FORMAT_MM_dd_yyyy;
//        String dateStr = getFormattedDateFromPattern(date, pattern);
//        return dateStr;
//    }
//    
//    public static final String getFormattedDateYMD(String dateString) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//    	String dateStr = getFormattedDateTime(formatter.parse(dateString), DATE_FORMAT_YYYY_MM_DD,"UTC");
////		SimpleDateFormat formatter1 = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
////        Date date = formatter.parse(dateString);
//       // String dateStr = formatter1.format(date);
//        return dateStr;
//    }
//
//    /**
//     * Removes the single coat from string.
//     * 
//     * @param string
//     *            the string
//     * @return the string
//     */
//    public static String removeSingleCoatFromString(String string) {
//        StringTokenizer st = new StringTokenizer(string, "'", true);
//        StringBuilder builder = new StringBuilder();
//        while (st.hasMoreTokens()) {
//            String token = st.nextToken();
//            if (!token.equals("'")) {
//                builder.append(token);
//            }
//        }
//        return builder.toString();
//    }
//
//    /**
//     * Convert map to json array.
//     * 
//     * @param map
//     *            the map
//     * @return the jSON array
//     */
//    @SuppressWarnings("rawtypes")
//    public static final JSONArray convertMapToJSONArray(HashMap<String, String> map) {
//        JSONArray mainArray = new JSONArray();
//        Set set = map.keySet();
//        Iterator i = set.iterator();
//
//        while (i.hasNext()) {
//            JSONArray array = new JSONArray();
//            String key = (String) i.next();
//            array.add(key);
//            array.add(map.get(key));
//            mainArray.add(array);
//        }
//        return mainArray;
//
//    }
//
//    /**
//     * Method: String getUniqueTicketCombination() Description: Gets the unique
//     * ticket combination.
//     * 
//     * @return the unique ticket combination
//     */
//    public static final String getUniqueTicketCombination() {
//        int timeCount = 2;
//        int numberOfDigits = 8;
//        int randomCount = numberOfDigits - timeCount;
//        StringBuffer ticket = new StringBuffer();
//        long currentTime = System.currentTimeMillis();
//        String randomAN = RandomStringUtils.randomAlphanumeric(numberOfDigits);
//        ticket.append(randomAN);
//        final Random randomCode = new Random();
//        final Random randomTime = new Random();
//        // time in miliseconds
//        final String sTime = Long.toString(Long.valueOf(currentTime++));
//        while (timeCount > 0) {
//            // index of codes to inject character
//            final int rIndex = randomCode.nextInt(randomCount);
//            // index of miliseconds to get injecting character
//            final int tIndex = randomTime.nextInt(sTime.length());
//            ticket.insert(rIndex, sTime.charAt(tIndex));
//            timeCount--;
//        }
//
//        return ticket.toString();
//    }
//
//    /**
//     * Gets the date difference.
//     * 
//     * @param dateStart
//     *            the date start
//     * @param dateStop
//     *            the date stop
//     * @return the date difference
//     * @throws ParseException
//     *             the parse exception
//     */
//    public static final long getDateDifference(String dateStart, String dateStop) throws ParseException {
//        String pattern = "MM/dd/yy HH:mm:ss";
//        Date dt_dateStart = getDateFromPattern(dateStart, pattern);
//        Date dt_dateStop = getDateFromPattern(dateStop, pattern);
//        long diff = dt_dateStart.getTime() - dt_dateStop.getTime();
//        return diff;
//    }
//
//    /**
//     * Gets the date difference in seconds.
//     * 
//     * @param dateStart
//     *            the date start
//     * @param dateStop
//     *            the date stop
//     * @return the date difference in seconds
//     * @throws ParseException
//     *             the parse exception
//     */
//    public static final long getDateDifferenceInSeconds(String dateStart, String dateStop) throws ParseException {
//        long diff = getDateDifference(dateStart, dateStop);
//        long diffSeconds = diff / 1000;
//        return diffSeconds;
//    }
//
//    /**
//     * Gets the date difference in minutes.
//     * 
//     * @param dateStart
//     *            the date start
//     * @param dateStop
//     *            the date stop
//     * @return the date difference in minutes
//     * @throws ParseException
//     *             the parse exception
//     */
//    public static final long getDateDifferenceInMinutes(String dateStart, String dateStop) throws ParseException {
//        long diff = getDateDifference(dateStart, dateStop);
//        long diffMinutes = diff / (60 * 1000);
//        return diffMinutes;
//    }
//
//    /**
//     * Gets the date difference in hours.
//     * 
//     * @param dateStart
//     *            the date start
//     * @param dateStop
//     *            the date stop
//     * @return the date difference in hours
//     * @throws ParseException
//     *             the parse exception
//     */
//    public static final long getDateDifferenceInHours(String dateStart, String dateStop) throws ParseException {
//        long diff = getDateDifference(dateStart, dateStop);
//        long diffHours = diff / (60 * 60 * 1000);
//        return diffHours;
//    }
//
//    // public static String encrypt(String Data) throws Exception {
//    // Key key = generateKey();
//    // Cipher c = Cipher.getInstance(ALGO);
//    // c.init(Cipher.ENCRYPT_MODE, key);
//    // byte[] encVal = c.doFinal(Data.getBytes());
//    // String encryptedValue = new BASE64Encoder().encode(encVal);
//    // return encryptedValue;
//    // }
//    //
//    // public static String decrypt(String encryptedData) throws Exception {
//    // Key key = generateKey();
//    // Cipher c = Cipher.getInstance(ALGO);
//    // c.init(Cipher.DECRYPT_MODE, key);
//    // byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
//    // byte[] decValue = c.doFinal(decordedValue);
//    // String decryptedValue = new String(decValue);
//    // return decryptedValue;
//    // }
//
//    /**
//     * Generate key.
//     * 
//     * @return the key
//     * @throws Exception
//     *             the exception
//     */
//    private static final Key generateKey() throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGO);
//        return key;
//    }
//
//    /**
//     * Gets the formatted user details for displaying in Created By and Modified
//     * By fields.
//     * 
//     * @param user
//     *            the user
//     * @return the formatted user details
//     */
//    public static final String getFormattedUserDetails(CCOUser user) {
//        if (isNullOrEmpty(user.getCcoid())) {
//            return "";
//        }
//
//        String details = user.getFirstName() + " " + user.getLastName() + " (" + user.getCcoid() + ")";
//        return details;
//    }
//
//    /**
//     * Gets the formatted date and time.
//     * 
//     * @param date
//     *            the date
//     * @return the formatted date time
//     * @throws ParseException
//     *             the parse exception
//     */
    public static final String getFormattedDateTime(Date date) throws ParseException {
        String pattern = DATE_TIME_FORMAT;
        String dateStr = getFormattedDateFromPattern(date, pattern);
        return dateStr;
    }

    public static final String getFormattedDateTime(Date date, String pattern) throws ParseException {
        String dateStr = getFormattedDateFromPattern(date, pattern);
        return dateStr;
    }

//    public static int getLimit(SearchCriteria searchCriteria) {
//        int limit = 0;
//        int count = searchCriteria.getTotalRecords();
//        int iDisplayLength = searchCriteria.getiDisplayLength();
//        int iDisplayStart = searchCriteria.getiDisplayStart();
//        if (count > iDisplayStart && count < (iDisplayStart + iDisplayLength)) {
//            limit = count - iDisplayStart;
//        } else {
//            limit = searchCriteria.getiDisplayLength();
//        }
//        return limit;
//    }
//
//    public static String unmarshal(Object object) {
//        String XMLObj = null;
//        try {
//            final StringWriter stringWriter = new StringWriter();
//            JAXBContext context = JAXBContext.newInstance(OrderResponse.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            marshaller.marshal(object, stringWriter);
//            XMLObj = new String(stringWriter.getBuffer());
//        } catch (Exception ex) {
//            return null;
//        }
//        return XMLObj;
//    }
//
//    public static Date getXSDate(String date) {
//        try {
//            return DatatypeConverter.parseDate(date).getTime();
//        } catch (Exception e) {
//        }
//        return null;
//    }
//    
//
//    public static int getInt(String number) {
//        try {
//            return Integer.parseInt(number);
//        } catch (Exception e) {
//
//        }
//        return -1;
//    }
//
//    public static Long getLong(String number) {
//        try {
//            return Long.parseLong(number);
//        } catch (Exception e) {
//
//        }
//        return -1L;
//    }
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static void getLikeArgument(List paramContainer, String argu) {
//        String str = escapeBackSlash(argu);
//        paramContainer.add("%" + escapePercent(str) + "%");
//    }
//
//    public static String getLikeArgument(String argu) {
//        String str = escapeBackSlash(argu);
//        return "%" + escapePercent(str) + "%";
//    }
//
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static void getEqualArgument(List paramContainer, String argu) {
//        paramContainer.add(escapeBackSlash(argu));
//    }
//
//    public static String getEqualArgument(String argu) {
//        return escapeBackSlash(argu);
//    }
//
//    public static String escapeBackSlash(String string) {
//        StringTokenizer st = new StringTokenizer(string, "\\", true);
//        StringBuilder builder = new StringBuilder();
//        while (st.hasMoreTokens()) {
//            String token = st.nextToken();
//            if (!token.equals("\\")) {
//                builder.append(token);
//            } else {
//                builder.append("\\\\");
//            }
//        }
//        return builder.toString();
//    }
//
//    public static String escapePercent(String string) {
//        StringTokenizer st = new StringTokenizer(string, "%", true);
//        StringBuilder builder = new StringBuilder();
//        while (st.hasMoreTokens()) {
//            String token = st.nextToken();
//            if (!token.equals("%")) {
//                builder.append(token);
//            } else {
//                builder.append("\\%");
//            }
//        }
//        return builder.toString();
//    }
//
//    public static String escapeChar(String str) {
//        return jsonEscape(str);
//    }
//
//    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//
//    public static String jsonEscape(String str) {
//
//        if (str == null || str.length() == 0) {
//            return "";
//        }
//        StringBuffer sb = new StringBuffer();
//        char current;
//        for (int i = 0, j = str.length(); i < j; ++i) {
//            current = str.charAt(i);
//            switch (current) {
//            case '\'':
//                sb.append("\\u0027");
//                break;
//            case '\"':
//                sb.append("\\u0022");
//                break;
//            case '\\':
//                sb.append('\\');
//                sb.append(current);
//                break;
//            case '<':
//                sb.append("\\u003c");
//                break;
//            case '>':
//                sb.append("\\u003e");
//                break;
//            default:
//                if (current < ' ' || (current >= '\u0080' && current < '\u00a0') || (current >= '\u2000' && current < '\u2100')) {
//                    sb.append('\\');
//                    switch (current) {
//                    case '\b':
//                        sb.append('b');
//                        break;
//                    case '\t':
//                        sb.append('t');
//                        break;
//                    case '\n':
//                        sb.append('n');
//                        break;
//                    case '\f':
//                        sb.append('f');
//                        break;
//                    case '\r':
//                        sb.append('r');
//                        break;
//                    default:
//
//                        sb.append('u');
//                        sb.append(HEX_DIGITS[(current >> 12) & 0xF]);
//                        sb.append(HEX_DIGITS[(current >> 8) & 0xF]);
//                        sb.append(HEX_DIGITS[(current >> 4) & 0xF]);
//                        sb.append(HEX_DIGITS[current & 0xF]);
//                    }
//                } else {
//                    sb.append(current);
//                }
//            }
//        }
//        return sb.toString();
//    }
//
//    public static Date addHours(int hours, Date iDate) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(iDate);
//        calendar.add(Calendar.HOUR, hours);
//        Date rDate = calendar.getTime();
//        return rDate;
//    }
//
//    public static String getFormattedDateTime(Date date, String pattern, String timeZone) {
//        String dateStr = "";
//        if (!isNullOrEmpty(date)) {
//            if (isNullOrEmpty(pattern)) {
//                pattern = DATE_TIME_FORMAT;
//            }
//            SimpleDateFormat format = new SimpleDateFormat(pattern);
//            if (!isNullOrEmpty(timeZone)) {
//                format.setTimeZone(TimeZone.getTimeZone(timeZone));
//
//            }
//            dateStr = format.format(date);
//        }
//        return dateStr;
//    }
//
//    /*
//     * THis method calculate difference between two time zones in hours which is
//     * used to show date in specific format with given time zone .
//     */
//    public static int getHours(String iTimeZone, String sTimeZone) {
//
//        if (isNullOrEmpty(iTimeZone)) {
//            iTimeZone = "GMT";
//        }
//
//        if (isNullOrEmpty(sTimeZone)) {
//            sTimeZone = timeZone;
//        }
//        Calendar c = new GregorianCalendar(TimeZone.getTimeZone(iTimeZone));
//        c.setTimeInMillis(new Date().getTime());
//        int EastCoastHourOfDay = c.get(Calendar.HOUR_OF_DAY);
//        int EastCoastDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//
//        // time zone set by client
//        c = new GregorianCalendar(TimeZone.getTimeZone(sTimeZone));
//        c.setTimeInMillis(new Date().getTime());
//        int AlaskaHourOfDay = c.get(Calendar.HOUR_OF_DAY);
//        int AlaskaDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
//
//        // Difference between client time zone and GMT
//        int hourDifference = EastCoastHourOfDay - AlaskaHourOfDay;
//        int dayDifference = EastCoastDayOfMonth - AlaskaDayOfMonth;
//        if (dayDifference != 0) {
//            hourDifference = hourDifference + 24;
//        }
//
//        return hourDifference;
//    }
//
//    /*
//     * iTimezone = initial time zone sTimezone = client provided time zone This
//     * method adds hours into provided date according to specified sTimezone
//     */
//    public static Date getTimezoneModifiedDate(Date iDate, String iTimezone, String sTimezone) {
//        if (isNullOrEmpty(iDate))
//            return null;
//        int hours = PLECommonsUtil.getHours(iTimezone, sTimezone);
//        Date rDate = PLECommonsUtil.addHours(hours, iDate);
//        return rDate;
//    }
//
//    /*
//     * iTimezone = initial time zone sTimezone = client provided time zone This
//     * method adds hours into provided date according to specified sTimezone and
//     * return formatted string
//     */
//    public static String getFormattedDate(Date iDate, String iTimezone, String sTimezone) {
//        try {
//            int hours = PLECommonsUtil.getHours(iTimezone, sTimezone);
//            Date rDate = PLECommonsUtil.addHours(hours, iDate);
//            String str = PLECommonsUtil.getFormattedDateTime(rDate);
//            return str;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static Date getReducedTimezoneModifiedDate(Date iDate, String iTimezone, String sTimezone) {
//        if (isNullOrEmpty(iDate))
//            return null;
//        int hours = PLECommonsUtil.getHours(iTimezone, sTimezone);
//        Date rDate = PLECommonsUtil.addHours(-hours, iDate);
//        return rDate;
//    }
//
//    public static Calendar setZeroTime(Calendar cal) {
//        cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
//        cal.set(Calendar.MINUTE, 0); // set minute in hour
//        cal.set(Calendar.SECOND, 0); // set second in minute
//        cal.set(Calendar.MILLISECOND, 0);
//        return cal;
//    }
//
//    public static Calendar setEndTime(Calendar cal) {
//        cal.set(Calendar.HOUR_OF_DAY, 23);
//        cal.set(Calendar.MINUTE, 59);
//        cal.set(Calendar.SECOND, 59);
//        cal.set(Calendar.MILLISECOND, 999); // credit to f1sh
//        return cal;
//    }
//
    public static String getMessage(String message) {
        // Locale locale1=LocaleContextHolder.getLocaleContext().getLocale();

        // ApplicationContext
        // context=ApplicationContextProvider.getApplicationContext();

        ResourceBundle resource = I18NResourceHandler.getResourceBundle(locale);
        return resource.getString(message);
    }

//    public static Course[] extendArray(Course objArray[], int newSize) {
//        Course newArray[] = new Course[objArray.length + newSize];
//        System.arraycopy(objArray, 0, newArray, 0, objArray.length);
//        return newArray;
//    }
//
//    public static String createSafeString(String strToEncode) {
//        Encoder encoder = ESAPI.encoder();
//        Codec MYSQL_CODEC = new MySQLCodec(Mode.STANDARD);
//        strToEncode = encoder.encodeForCSS(strToEncode);
//        strToEncode = encoder.encodeForHTML(strToEncode);
//        strToEncode = encoder.encodeForJavaScript(strToEncode);
//        strToEncode = encoder.encodeForXML(strToEncode);
//        strToEncode = encoder.encodeForXPath(strToEncode);
//        strToEncode = encoder.encodeForSQL(MYSQL_CODEC, strToEncode);
//        return strToEncode;
//    }
//
//    public static Cache getPLENonceCache() {
//        CacheManager cacheManager = CacheManager.create();
//        Cache cache = cacheManager.getCache(PLE_NONCE_CACHE);
//        return cache;
//    }
//
//    public static Cache getAuthorityCache() {
//        CacheManager cacheManager = CacheManager.create();
//        Cache cache = cacheManager.getCache(PLE_PRINCIPAL_CACHE);
//        return cache;
//    }
//    
//    
//    public static int compareDkitMasterList(DkitMaster dkitMasterOne, DkitMaster dkitMasterTwo) {
//    	return compareDkitVersionsAsFloat(dkitMasterOne.getDigital_kit_version() + "." + dkitMasterOne.getMinor_version(), dkitMasterTwo.getDigital_kit_version() + "." + dkitMasterTwo.getMinor_version());
//    }
//    
//    public static String getPartialCCSId(boolean needPartialData, String ccsId) {
//		String partialCCSId = "";
//		String delimiter = "/";
//		String[] temp = ccsId.split(delimiter);
//		partialCCSId = temp[0] + "/" + temp[1];
//		if (needPartialData) {
//			return partialCCSId;
//		} else {
//			return temp[2];
//		}
//
//	}
//    
//    public static void splitAndSetSKUVersion(DkitMaster dkitMaster) {
//		String[] parts = dkitMaster.getSkuVersion().split("\\.");
//		String part1 = parts[0];
//		String part2 = parts[1];
//		dkitMaster.setDigital_kit_version(part1);
//		dkitMaster.setMinor_version(part2);
//	}
//    public static void setSKUVersion(DkitMaster dkitMaster) {
//		dkitMaster.setSkuVersion(dkitMaster.getDigital_kit_version() +"."+ dkitMaster.getMinor_version());
//	}
//    
//    public static int compareDkitVersionsAsFloat(String dkitOne, String dkitTwo) {
//    	  float v1 = Float.parseFloat(dkitOne);
//    	  float v2 = Float.parseFloat(dkitTwo); 
//    	  if(v1<v2){
//    		  return -1;
//    	  } else if (v1 == v2){
//    		  return 0;
//    	  } else {
//    		  return 1;
//    	  } 
//    }
//    
//    public static boolean shouldUpdateContent(DkitMaster lastDkitMasterDB, DkitMaster newDkitMasterDB, SkuUpdateFlagHolder flagHolder) {
//		PLECommonsUtil.setSKUVersion(lastDkitMasterDB);
//		PLECommonsUtil.setSKUVersion(newDkitMasterDB);
//		getSKUUpdateFlags(lastDkitMasterDB, newDkitMasterDB, flagHolder);
//
//		if (flagHolder.isMinorUpdate()) {
//			if (flagHolder.isMinorUpdateFreeFlag())
//				return true;
//			else if (!flagHolder.isMinorUpdateFreeFlag() && flagHolder.isPresentInAcroynmList())
//				return true;
//		}
//
//		if (flagHolder.isMajorUpdate()) {
//			if (flagHolder.isMajorUpdateFreeFlag())
//				return true;
//			else if (!flagHolder.isMajorUpdateFreeFlag() && flagHolder.isPresentInAcroynmList())
//				return true;
//		}
//		return false;
//	}
//    
//    public static boolean shouldUpdateContentForward(DkitMaster lastDkitMasterDB, DkitMaster newDkitMasterDB, SkuUpdateFlagHolder flagHolder) {
//		PLECommonsUtil.setSKUVersion(lastDkitMasterDB);
//		PLECommonsUtil.setSKUVersion(newDkitMasterDB);
//		getSKUUpdateFlagsForward(lastDkitMasterDB, newDkitMasterDB, flagHolder);
//
//		if (flagHolder.isMinorUpdate()) {
//			if (flagHolder.isMinorUpdateFreeFlag())
//				return true;
//			else if (!flagHolder.isMinorUpdateFreeFlag() && flagHolder.isPresentInAcroynmList())
//				return true;
//		}
//		else if (flagHolder.isMajorUpdate()) {
//			if (flagHolder.isMajorUpdateFreeFlag())
//				return true;
//			else if (!flagHolder.isMajorUpdateFreeFlag() && flagHolder.isPresentInAcroynmList())
//				return true;
//		}
//		return false;
//	}
//    
//    
//    private static void getSKUUpdateFlagsForward(DkitMaster lastDkitMasterDB,
//			DkitMaster newDkitMasterDB, SkuUpdateFlagHolder flagHolder) {
//
//		//SkuUpdateFlagHolder flagHolder = new SkuUpdateFlagHolder();
//		
//		// Check operation 
//		if (((int)(Float.parseFloat(newDkitMasterDB.getSkuVersion())) - (int)(Float.parseFloat(lastDkitMasterDB.getSkuVersion())) < 1) && (Integer.parseInt(newDkitMasterDB.getDigital_kit_version())==Integer.parseInt(lastDkitMasterDB.getDigital_kit_version()))) {
//			flagHolder.setMinorUpdate(true);
//
//			flagHolder.setMinorUpdateFreeFlag(getBooleanFromProperty(PLEServiceConstants.FREE_MINOR_UPDATE_ALL));
//			flagHolder.setMinorSKUAnnotationMigrationEnabled(getBooleanFromProperty(PLEServiceConstants.ANNOTATION_MIGRATION_MINOR_SKU));
//			flagHolder.setFreeMinorUpdateAcronyms(splitAndGetAcroymList(newDkitMasterDB.getAcronym(),
//					PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED));
//			try {
//				if (!PLECommonsUtil.isNullOrEmpty(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED)))
//					flagHolder.setPresentInAcroynmList(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED)
//							.toLowerCase().contains(newDkitMasterDB.getAcronym().toLowerCase()));
//				
//				if(flagHolder.isMinorSKUAnnotationMigrationEnabled())
//					flagHolder.setAnnotationMigrationChoiceEnabled(true);
//			} catch (InvalidPLEPropertyException e) {
//				flagHolder.setPresentInAcroynmList(false);
//				e.printStackTrace();
//			}
//
//		} else {
//			flagHolder.setMajorUpdate(true);
//
//			flagHolder.setMajorUpdateFreeFlag(getBooleanFromProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_ALL));
//			flagHolder.setMajorSKUAnnotationMigrationEnabled(getBooleanFromProperty(PLEServiceConstants.ANNOTATION_MIGRATION_MAJOR_SKU));
//			flagHolder.setFreeMajorUpdateAcronyms(splitAndGetAcroymList(newDkitMasterDB.getAcronym(),
//					PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED));
//			try {
//				if (!PLECommonsUtil.isNullOrEmpty(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED)))
//					flagHolder.setPresentInAcroynmList(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED)
//							.toLowerCase().contains(newDkitMasterDB.getAcronym().toLowerCase()));
//				
//				if(flagHolder.isMajorSKUAnnotationMigrationEnabled())
//					flagHolder.setAnnotationMigrationChoiceEnabled(true);
//			} catch (InvalidPLEPropertyException e) {
//				flagHolder.setPresentInAcroynmList(false);
//				e.printStackTrace();
//			}
//
//		}
// 
//	
//		
//	}
//
//	private static void getSKUUpdateFlags(DkitMaster lastDkitMasterDB, DkitMaster newDkitMasterDB, SkuUpdateFlagHolder flagHolder) {
//		//SkuUpdateFlagHolder flagHolder = new SkuUpdateFlagHolder();
//		
//		// Check operation 
//		if ((int)(Float.parseFloat(newDkitMasterDB.getSkuVersion())) - (int)(Float.parseFloat(lastDkitMasterDB.getSkuVersion())) < 1) {
//			flagHolder.setMinorUpdate(true);
//
//			flagHolder.setMinorUpdateFreeFlag(getBooleanFromProperty(PLEServiceConstants.FREE_MINOR_UPDATE_ALL));
//			flagHolder.setMinorSKUAnnotationMigrationEnabled(getBooleanFromProperty(PLEServiceConstants.ANNOTATION_MIGRATION_MINOR_SKU));
//			flagHolder.setFreeMinorUpdateAcronyms(splitAndGetAcroymList(newDkitMasterDB.getAcronym(),
//					PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED));
//			try {
//				if (!PLECommonsUtil.isNullOrEmpty(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED)))
//					flagHolder.setPresentInAcroynmList(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MINOR_UPDATE_SELECTED)
//							.toLowerCase().contains(newDkitMasterDB.getAcronym().toLowerCase()));
//				
//				if(flagHolder.isMinorSKUAnnotationMigrationEnabled())
//					flagHolder.setAnnotationMigrationChoiceEnabled(true);
//			} catch (InvalidPLEPropertyException e) {
//				flagHolder.setPresentInAcroynmList(false);
//				e.printStackTrace();
//			}
//
//		} else {
//			flagHolder.setMajorUpdate(true);
//
//			flagHolder.setMajorUpdateFreeFlag(getBooleanFromProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_ALL));
//			flagHolder.setMajorSKUAnnotationMigrationEnabled(getBooleanFromProperty(PLEServiceConstants.ANNOTATION_MIGRATION_MAJOR_SKU));
//			flagHolder.setFreeMajorUpdateAcronyms(splitAndGetAcroymList(newDkitMasterDB.getAcronym(),
//					PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED));
//			try {
//				if (!PLECommonsUtil.isNullOrEmpty(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED)))
//					flagHolder.setPresentInAcroynmList(PLEPropertiesUtil.getProperty(PLEServiceConstants.FREE_MAJOR_UPDATE_SELECTED)
//							.toLowerCase().contains(newDkitMasterDB.getAcronym().toLowerCase()));
//				
//				if(flagHolder.isMajorSKUAnnotationMigrationEnabled())
//					flagHolder.setAnnotationMigrationChoiceEnabled(true);
//			} catch (InvalidPLEPropertyException e) {
//				flagHolder.setPresentInAcroynmList(false);
//				e.printStackTrace();
//			}
//
//		}
// 
//	}
//    
//    private static boolean getBooleanFromProperty(String property) {
//		String flagValue;
//		try {
//			flagValue = PLEPropertiesUtil.getProperty(property);
//			if (!PLECommonsUtil.isNullOrEmpty(flagValue) && flagValue.equalsIgnoreCase("y"))
//				return true;
//		} catch (InvalidPLEPropertyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return false;
//	}
//    
//    private static List<String> splitAndGetAcroymList(String acronym, String propertyKey) {
//		try {
//			String acroymPropertyValue = PLEPropertiesUtil.getProperty(propertyKey);
//			if (!PLECommonsUtil.isNullOrEmpty(acroymPropertyValue)) {
//				return new ArrayList<String>(Arrays.asList(acroymPropertyValue.split("\\n")));
//			}
//		} catch (InvalidPLEPropertyException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//    
//    public static int compareVersions(String v1, String v2) {
//
//		if (v1 == null || v2 == null || v1.trim().equals("") || v2.trim().equals(""))
//			return -2;
//		else if (v1.equals(v2))
//			return 0;
//		else {
//			boolean valid1 = v1.matches("\\d+\\.\\d+\\.\\d+");
//			boolean valid2 = v2.matches("\\d+\\.\\d+\\.\\d+");
//
//			if (valid1 && valid2) {
//				int[] nums1;
//				int[] nums2;
//
//				try {
//					nums1 = convertStringArrayToIntArray(v1.split("\\."));
//					nums2 = convertStringArrayToIntArray(v2.split("\\."));
//				} catch (NumberFormatException e) {
//					return -2;
//				}
//
//				if (nums1.length == 3 && nums2.length == 3) {
//					if (nums1[0] < nums2[0])
//						return -1;
//					else if (nums1[0] > nums2[0])
//						return 1;
//					else {
//						if (nums1[1] < nums2[1])
//							return -1;
//						else if (nums1[1] > nums2[1])
//							return 1;
//						else {
//							if (nums1[2] < nums2[2])
//								return -1;
//							else if (nums1[2] > nums2[2])
//								return 1;
//							else {
//								return 0;
//							}
//						}
//					}
//				} else {
//					return -2;
//				}
//			} else {
//				return -2;
//			}
//		}
//	}
//
//    
//    private static int[] convertStringArrayToIntArray(String[] stringArray) throws NumberFormatException {
//		if (stringArray != null) {
//			int intArray[] = new int[stringArray.length];
//			for (int i = 0; i < stringArray.length; i++) {
//				intArray[i] = Integer.parseInt(stringArray[i]);
//			}
//			return intArray;
//		}
//		return null;
//	}
//   

}
