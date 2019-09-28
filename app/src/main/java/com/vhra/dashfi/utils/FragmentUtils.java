package com.vhra.dashfi.utils;

import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.vhra.dashfi.R;

public class FragmentUtils {
    private static final String TAG = "FragmentUtils";

    public static void replaceFragment(
            final FragmentActivity activity,
            final Fragment fragment,
            final String fragmentTag,
            final String backStackTag) {
        if (activity == null) {
            Log.d(TAG, "Error pop backing: Activity is null!");
            return;
        }

        if (fragment == null) {
            Log.d(TAG, "Error replacing Fragment: Fragment is null!");
            return;
        }

        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment, fragmentTag);
        if (!TextUtils.isEmpty(backStackTag)) {
            transaction.addToBackStack(backStackTag);
        }
        transaction.commit();
    }

    public static void popBackStackTo(final FragmentActivity activity, final String fragmentTag) {
        if (activity == null) {
            Log.d(TAG, "Error pop backing: Activity is null!");
            return;
        }

        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment != null && !fragment.isAdded()) {
            activity.getSupportFragmentManager().popBackStack(fragmentTag, 0);
        }
    }

    public static boolean isFragmentInflated(
            final FragmentActivity activity, final String fragmentTag) {
        if (activity == null) {
            Log.d(TAG, "Error checking fragment: Activity is null!");
            return false;
        }
        return activity.getSupportFragmentManager().findFragmentByTag(fragmentTag) != null;
    }
}
