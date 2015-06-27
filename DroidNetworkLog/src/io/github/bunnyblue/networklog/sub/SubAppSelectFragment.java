package io.github.bunnyblue.networklog.sub;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.bunnyblue.networklog.AppItem;
import io.github.bunnyblue.networklog.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubAppSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubAppSelectFragment extends Fragment {


    List<AppItem> appItemList=new ArrayList<AppItem>();
    ListView mAppListView;
    ApkAdapter mApkAdapter;
    List<AppItem> apkPaths;
    private int checkNum = 0; // 记录选中的条目数量
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SubAppSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubAppSelectFragment newInstance() {
        SubAppSelectFragment fragment = new SubAppSelectFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public SubAppSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_app_select, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAppListView= (ListView) view.findViewById(R.id.listviewApps);
        init();
        mApkAdapter=new ApkAdapter(appItemList,getActivity());
        mAppListView.setAdapter(mApkAdapter);
        initListviewClick();

    }

    private void initListviewClick() {

        mAppListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ApkAdapter.ViewHolder holder = (ApkAdapter.ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.apkCheckBox.toggle();
                // 将CheckBox的选中状况记录下来
                mApkAdapter.getIsSelected().put(arg2, holder.apkCheckBox.isChecked());
                // 调整选定条目
                if (holder.apkCheckBox.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                // 用TextView显示
                // tv_show.setText("已选中" + checkNum + "项");

            }
        });

    }

    private void unselectAll() {

        // 遍历list的长度，将MyAdapter中的map值全部设为true
        for (int i = 0; i < apkPaths.size(); i++) {
            mApkAdapter.getIsSelected().put(i, false);
        }
        // 数量设为list的长度
        checkNum = apkPaths.size();
        // 刷新listview和TextView的显示
        dataChanged();

    }
    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        mApkAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        // tv_show.setText("已选中" + checkNum + "项");
    }
    private  void  init(){
        appItemList.clear();
        List<PackageInfo> packages = getActivity().getPackageManager().getInstalledPackages(0);

        for (int j = 0; j < packages.size(); j++) {
            Map<String, Object> map = new HashMap<String, Object>();
            PackageInfo packageInfo = packages.get(j);
            //显示非系统软件
            if((packageInfo.applicationInfo.flags& ApplicationInfo.FLAG_SYSTEM)==0){
                AppItem  appItem=new AppItem();
                appItem.icon=packageInfo.applicationInfo.loadIcon(getActivity().getPackageManager()).getCurrent();
              appItem.name=packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                appItem.packageName=packageInfo.packageName;
                appItemList.add(appItem);
            }
        }
    }




}
