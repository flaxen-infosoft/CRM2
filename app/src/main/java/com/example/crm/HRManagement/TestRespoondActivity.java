package com.example.crm.HRManagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.crm.R;
import com.example.crm.SalesManagement.LeaveActivity;
import com.example.crm.SalesManagement.LeaveApprovedFragment;
import com.example.crm.SalesManagement.LeaveDeclinedFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TestRespoondActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_respoond);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        adapter= new MainAdapter(getSupportFragmentManager());
        adapter.AddFragment(new IndividualTestFragment(), "Individual");
        adapter.AddFragment(new SummeryTestFragment(), "Summery");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void AddFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return  fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return stringArrayList.get(position);
        }
    }
}