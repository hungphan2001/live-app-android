package io.hypertrack.sendeta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

import io.hypertrack.sendeta.R;
import io.hypertrack.sendeta.adapter.callback.UserActivitiesOnClickListener;
import io.hypertrack.sendeta.model.UserActivityModel;
import io.hypertrack.sendeta.util.images.RoundedImageView;

/**
 * Created by piyush on 02/09/16.
 */
public class ReceivedActivitiesAdapter extends RecyclerView.Adapter<ReceivedActivitiesAdapter.ReceivedActivitiesViewHolder> {

    private Context mContext;
    private ArrayList<UserActivityModel> userActivities = new ArrayList<>();
    private UserActivitiesOnClickListener listener;

    public ReceivedActivitiesAdapter(Context mContext, Collection<UserActivityModel> userActivities, UserActivitiesOnClickListener listener) {
        this.mContext = mContext;
        this.userActivities.addAll(userActivities != null ? userActivities : new ArrayList<UserActivityModel>());
        this.listener = listener;
    }

    public void setUserActivities(Collection<UserActivityModel> userActivities) {
        this.userActivities.addAll(userActivities);
        notifyDataSetChanged();
    }

    @Override
    public ReceivedActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user_activity, parent, false);
        return new ReceivedActivitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceivedActivitiesViewHolder holder, int position) {
        UserActivityModel activity = userActivities.get(position);
        if (activity != null) {

            if (!TextUtils.isEmpty(activity.getTitle())) {
                holder.activityTitle.setText(activity.getTitle());
                holder.activityTitle.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(activity.getSubtitle())) {
                holder.activitySubtitle.setText(activity.getSubtitle());
                holder.activitySubtitleLayout.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(activity.getDate())) {
                holder.activityDate.setText(activity.getDate());
                holder.activityDate.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(activity.getDriverImageURL())) {
                Picasso.with(mContext)
                        .load(activity.getDriverImageURL())
                        .placeholder(R.drawable.default_profile_pic)
                        .error(R.drawable.default_profile_pic)
                        .into(holder.activityLayoutMainIcon);
                holder.activityLayoutMainIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            if (!TextUtils.isEmpty(activity.getEndAddress())) {
                holder.activityEndAddress.setText(activity.getEndAddress());
                holder.endAddressLayoutIcon.setVisibility(View.VISIBLE);
                holder.activityEndAddressLayout.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(activity.getEndTime())) {
                holder.activityEndTime.setText(activity.getEndTime());
                holder.activityEndTime.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(activity.getStartAddress())) {
                holder.activityStartAddress.setText(activity.getStartAddress());
                holder.startAddressLayoutIcon.setVisibility(View.VISIBLE);
                holder.activityStartAddressLayout.setVisibility(View.VISIBLE);

                if (holder.activityEndAddressLayout.getVisibility() == View.VISIBLE) {
                    holder.startEndIconVerticalSeparator.setVisibility(View.VISIBLE);
                    holder.startEndAddressHorizontalSeparator.setVisibility(View.VISIBLE);
                }
            }

            if (!TextUtils.isEmpty(activity.getStartTime())) {
                holder.activityStartTime.setText(activity.getStartTime());
                holder.activityStartTime.setVisibility(View.VISIBLE);
            }

            if (activity.isInProcess()) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(0, 0, 0, 0);
                holder.activityAddressIconsLayout.setLayoutParams(layoutParams);

            } else {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                int margin = mContext.getResources().getDimensionPixelSize(R.dimen.margin_very_low);
                layoutParams.setMargins(0, margin, 0, margin);
                holder.activityAddressIconsLayout.setLayoutParams(layoutParams);
            }
        }
    }

    @Override
    public int getItemCount() {
        return userActivities.size();
    }

    public class ReceivedActivitiesViewHolder extends RecyclerView.ViewHolder {

        private TextView activityTitle, activitySubtitle, activityDate, activityStartAddress,
                activityEndAddress, activityStartTime, activityEndTime;
        private RoundedImageView activityLayoutMainIcon;
        private ImageView startAddressLayoutIcon, endAddressLayoutIcon;
        private View startEndIconVerticalSeparator, startEndAddressHorizontalSeparator;
        private LinearLayout activitySubtitleLayout, activityAddressIconsLayout,
                activityStartAddressLayout, activityEndAddressLayout;

        public ReceivedActivitiesViewHolder(View itemView) {
            super(itemView);
            activityTitle = (TextView) itemView.findViewById(R.id.item_user_activity_title);
            activitySubtitle = (TextView) itemView.findViewById(R.id.item_user_activity_subtitle_text);
            activityDate = (TextView) itemView.findViewById(R.id.item_user_activity_date);
            activityStartAddress = (TextView) itemView.findViewById(R.id.item_user_activity_start_address);
            activityEndAddress = (TextView) itemView.findViewById(R.id.item_user_activity_end_address);
            activityStartTime = (TextView) itemView.findViewById(R.id.item_user_activity_start_time);
            activityEndTime = (TextView) itemView.findViewById(R.id.item_user_activity_end_time);

            activityLayoutMainIcon = (RoundedImageView) itemView.findViewById(R.id.item_user_activity_icon);
            startAddressLayoutIcon = (ImageView) itemView.findViewById(R.id.item_user_activity_start_icon);
            endAddressLayoutIcon = (ImageView) itemView.findViewById(R.id.item_user_activity_end_icon);

            startEndAddressHorizontalSeparator = itemView.findViewById(R.id.item_user_activity_horizontal_separator);
            startEndIconVerticalSeparator = itemView.findViewById(R.id.item_user_activity_vertical_separator);

            activitySubtitleLayout = (LinearLayout) itemView.findViewById(R.id.item_user_activity_subtitle_layout);
            activityAddressIconsLayout = (LinearLayout) itemView.findViewById(R.id.item_user_activity_icons_layout);
            activityStartAddressLayout = (LinearLayout) itemView.findViewById(R.id.item_user_activity_start_address_layout);
            activityEndAddressLayout = (LinearLayout) itemView.findViewById(R.id.item_user_activity_end_address_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    // Check if a valid list item has been clicked
                    if (listener != null && position != RecyclerView.NO_POSITION &&
                            userActivities.size() > position && userActivities.get(position) != null) {

                        // Check if the clicked activity is InProcess or not
                        if (userActivities.get(position).isInProcess()) {
                            listener.OnInProcessActivityClicked(position, userActivities.get(position));
                        } else {
                            listener.OnHistoryActivityClicked(position, userActivities.get(position));
                        }
                    }
                }
            });
        }
    }
}
