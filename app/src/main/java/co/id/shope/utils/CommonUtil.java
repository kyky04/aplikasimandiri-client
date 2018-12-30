package co.id.shope.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import co.id.shope.R;


public class CommonUtil {

    static Gson gson;

    static {
        if (gson == null)
            gson = new GsonBuilder()
                    .setExclusionStrategies()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showSnack(Activity context, String message) {
        Snackbar.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }

    public static void setImage(Context context, String url, ImageView imageView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5.f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();

//        Glide.with(context).load(Constans.WEB_URL_IMAGE + url).placeholder(circularProgressDrawable).into(imageView);
    }



    public static String stringToDate(String tanggal) {
        SimpleDateFormat fromString = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fromString.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat fromDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateTime = fromDate.format(date);
        return dateTime;
    }

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = df.format(date);
        return stringDate;
    }

    public static String getHariFromDate(Date tanggal) {
        DateFormat format = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        String day = format.format(tanggal);
        switch (day) {
            case "Sunday":
                day = "MINGGU";
                break;
            case "Monday":
                day = "SENIN";
                break;
            case "Tuesday":
                day = "SELASA";
                break;
            case "Wednesday":
                day = "RABU";
                break;
            case "Thursday":
                day = "KAMIS";
                break;
            case "Friday":
                day = "JUMAT";
                break;
        }
        return day;
    }



    public static String getCurrentDate(String fmt) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
        SimpleDateFormat format = new SimpleDateFormat(fmt);
//        Date date = new Date();
        return format.format(cal.getTime());
    }



    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd");
    }

    public static String convertTanggalToHari(int num) {
        String hari = "";
        switch (num) {
            case 1:
                hari = "Senin";
                break;
            case 2:
                hari = "Selasa";
                break;
            case 3:
                hari = "Rabu";
                break;
            case 4:
                hari = "Kamis";
                break;
            case 5:
                hari = "Jumat";
                break;
            case 6:
                hari = "Sabtu";
                break;
            case 0:
                hari = "Minggu";
                break;
        }
        return hari;
    }

    public static String convertHari(int num) {
        String hari = "";
        switch (num) {
            case 1:
                hari = "SENIN";
                break;
            case 2:
                hari = "SELASA";
                break;
            case 3:
                hari = "RABU";
                break;
            case 4:
                hari = "KAMIS";
                break;
            case 5:
                hari = "JUMAT";
                break;
            case 6:
                hari = "SABTU";
                break;
            case 0:
                hari = "MINGGU";
                break;
        }
        return hari;
    }


    public static JSONObject convertStringToJson(String[] parameter, String[] value) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < parameter.length; i++) {
            try {
                jsonObject.put(parameter[i], value[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static boolean validateEmptyEntries(ArrayList<View> fields) {
        boolean check = true;
        for (int i = 0; i < fields.size(); i++) {
            EditText currentField = (EditText) fields.get(i);
            if (currentField.getText().toString().length() <= 0) {
                currentField.setError("Please fill out this field");
//                showToast("Please fill out this field");
                check = false;
            }
        }
        return check;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean verifyPermission(Context activity, String permissionCode) {
        if (ContextCompat.checkSelfPermission(activity, permissionCode) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String getFormattedPriceIndonesia(Double price) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String result = kursIndonesia.format(price);
        return result;
    }

    public static List<Date> getDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    public static void setSystemBarColor(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    /**
     * Making notification bar transparent
     */
    public static void setSystemBarTransparent(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void displayImageOriginal(Context ctx, ImageView img, @DrawableRes int drawable) {
        try {
            Glide.with(ctx).load(drawable)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static void displayImageRound(final Context ctx, final ImageView img, @DrawableRes int drawable) {
//        try {
//            Glide.with(ctx).load(drawable).into(new BitmapImageViewTarget(img) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(ctx.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    img.setImageDrawable(circularBitmapDrawable);
//                }
//            });
//        } catch (Exception e) {
//        }
    }

    public static void displayImageOriginal(Context ctx, ImageView img, String url) {
        try {
            Glide.with(ctx).load(url)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedDateEvent(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("EEE, MMM dd yyyy");
        return newFormat.format(new Date(dateTime));
    }

    public static String getFormattedTimeEvent(Long time) {
        SimpleDateFormat newFormat = new SimpleDateFormat("h:mm a");
        return newFormat.format(new Date(time));
    }

    public static String getEmailFromName(String name) {
        if (name != null && !name.equals("")) {
            String email = name.replaceAll(" ", ".").toLowerCase().concat("@mail.com");
            return email;
        }
        return name;
    }

    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



    public static void copyToClipboard(Context context, String data) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clipboard", data);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show();
    }

    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(new Runnable() {
            @Override
            public void run() {
                nested.scrollTo(500, targetView.getBottom());
            }
        });
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    public static boolean toggleArrow(boolean show, View view) {
        return toggleArrow(show, view, true);
    }

    public static boolean toggleArrow(boolean show, View view, boolean delay) {
        if (show) {
            view.animate().setDuration(delay ? 200 : 0).rotation(180);
            return true;
        } else {
            view.animate().setDuration(delay ? 200 : 0).rotation(0);
            return false;
        }
    }


    public static void changeMenuIconColor(Menu menu, @ColorInt int color) {
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable == null) continue;
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String toCamelCase(String input) {
        input = input.toLowerCase();
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
    public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

            final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
            Log.e("Can not set custom font" , customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
        }
    }

    public static boolean isEmpty(Collection coll) {
        return (coll == null || coll.isEmpty());
    }
}
