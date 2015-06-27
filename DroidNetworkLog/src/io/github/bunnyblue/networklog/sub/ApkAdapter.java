/**
 *
 */
package io.github.bunnyblue.networklog.sub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import io.github.bunnyblue.networklog.AppItem;
import io.github.bunnyblue.networklog.R;

/**
 * @author BunnyBlue
 *
 */

public class ApkAdapter extends BaseAdapter {

	private List<AppItem> list;

	private HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();;

	private Context context;

	private LayoutInflater inflater = null;


	public ApkAdapter(List<AppItem> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);


		initData();
	}


	private void initData() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public AppItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 获得ViewHolder对象
			holder = new ViewHolder();
			// 导入布局并赋值给convertview
			convertView = inflater.inflate(R.layout.item_apk, null);
			holder.apkName = (TextView) convertView.findViewById(R.id.apkName);
			holder.apkCheckBox = (CheckBox) convertView.findViewById(R.id.apkSelect);

			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

			holder.tvPkgName = (TextView) convertView.findViewById(R.id.tvPkgName);
			holder.tvVersionCode = (TextView) convertView.findViewById(R.id.tvVersionCode);
			holder.tvVersionName = (TextView) convertView.findViewById(R.id.tvVersionName);


			convertView.setTag(holder);
		} else {
		
			holder = (ViewHolder) convertView.getTag();
		}

		holder.apkName.setText(list.get(position).name);
		holder.imageView.setImageDrawable(list.get(position).icon);
		AppItem mApkItem = list.get(position);

		holder.tvPkgName.setText(mApkItem.packageName);
//		holder.tvVersionCode.setText(mApkItem.getVersionCode() + "");
//		holder.tvVersionName.setText(mApkItem.getVersionName());
		holder.apkCheckBox.setChecked(getIsSelected().get(position));
		return convertView;
	}

	public HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		this.isSelected = isSelected;
	}

	static class ViewHolder {
		TextView apkName;
		CheckBox apkCheckBox;

		TextView tvPkgName;
		TextView tvVersionCode;
		TextView tvVersionName;
		ImageView imageView;

	}
}
