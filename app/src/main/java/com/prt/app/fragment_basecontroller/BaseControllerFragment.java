package com.prt.app.fragment_basecontroller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ledexpo.android.R;

/**
 * @author Pratyesh Singh
 */

public class BaseControllerFragment extends Fragment {

	public void replaceFragment(Fragment fragment, boolean addToBackStack) {
		try {
			FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
			if (addToBackStack) {
				transaction.addToBackStack(null);
			}
			transaction.replace(R.id.framelayout_container, fragment);
			transaction.commit();
			// transaction.commitAllowingStateLoss();
			getChildFragmentManager().executePendingTransactions();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean popFragment() {
		Log.e("test", "pop fragment: " + getChildFragmentManager().getBackStackEntryCount());
		boolean isPop = false;
		if (getChildFragmentManager().getBackStackEntryCount() > 0) {
			isPop = true;
			getChildFragmentManager().popBackStack();
		}
		return isPop;
	}

}
