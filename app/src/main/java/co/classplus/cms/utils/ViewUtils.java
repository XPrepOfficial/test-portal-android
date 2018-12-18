package co.classplus.cms.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import co.classplus.cms.R;

public final class ViewUtils {

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static final String[] SECTION_COLORS = {
            "#00cb9f",
            "#007cff",
            "#ffbd00",
            "#00796B",
            "#ff7816",
            "#fc5463",
    };

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static void setImageWithDrawable(ImageView imageView, String imageUrl, Drawable drawable) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        if (drawable != null) {
            builder.showImageOnLoading(drawable);
            builder.showImageOnFail(drawable);
        } else {
            builder.showImageOnLoading(R.drawable.ic_launcher_foreground);
            builder.showImageOnFail(R.drawable.ic_launcher_foreground);
        }
        DisplayImageOptions displayImageOptions = builder.build();
        if (imageUrl == null || TextUtils.isEmpty(imageUrl)) {
            imageView.setImageDrawable(drawable);
        } else {
            ImageLoader.getInstance().displayImage(imageUrl, imageView, displayImageOptions);
        }
    }

    public static void setCircleImageWithDrawable(CircularImageView circularImageView, String imageUrl, String name) {
        TextDrawable drawable = getNameDrawable(name);
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        if (drawable != null) {
            builder.showImageOnLoading(drawable);
            builder.showImageOnFail(drawable);
        } else {
            builder.showImageOnLoading(R.drawable.ic_chevron_right);
            builder.showImageOnFail(R.drawable.ic_chevron_right);
        }
        DisplayImageOptions displayImageOptions = builder.build();
        if (imageUrl == null || TextUtils.isEmpty(imageUrl)) {
            circularImageView.setImageDrawable(drawable);
        } else {
            ImageLoader.getInstance().displayImage(imageUrl, circularImageView, displayImageOptions);
        }
    }

    public static TextDrawable getNameDrawable(String name) {
        if (name == null || name.equals("")) {
            name = "X";
        }
        name = name.trim();
        if (TextUtils.isEmpty(name)) {
            name = "X";
        }
        String[] caps = name.split("\\s+");
        String st_name = "";
        for (int i = 0; i < caps.length; i++) {
            if (i < 2) {
                st_name += caps[i].substring(0, 1).toUpperCase();
            } else {
                break;
            }
        }
        int color = Color.parseColor(SECTION_COLORS[Math.abs(name.hashCode()) % SECTION_COLORS.length]);
        return TextDrawable.builder()
                .buildRound(st_name, color);
    }

    public static void enableDisableTouch(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableTouch((ViewGroup) view, enabled);
            }
        }
    }
}
