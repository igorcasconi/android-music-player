package com.example.testapplicationjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplicationjava.R;
import com.example.testapplicationjava.models.AlbumOfTrack;
import com.example.testapplicationjava.models.TrackDataItem;
import com.example.testapplicationjava.models.TrackData;
import com.example.testapplicationjava.utils.DownloadImageTask;
import com.example.testapplicationjava.utils.TimeString;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
  private List<TrackData> trackList;
  private Context context;
  private TimeString timeString = new TimeString();

  public TrackAdapter(Context context, List<TrackData> trackList){
    this.context = context;
    this.trackList = trackList;
  }

  public void setList(List<TrackData> trackList){
    this.trackList = trackList;
  }

  @Override
  public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View itemTrackList = inflater.inflate(R.layout.track_item_list, parent, false);
    TrackViewHolder viewHolder = new TrackViewHolder(itemTrackList);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(TrackViewHolder holder, int position) {
    TrackDataItem trackItem = trackList.get(position).getData();
    String nameSong = trackItem.getName();
    long durationSong = trackItem.getDuration().getTotalMilliseconds();
    String duration = timeString.convertMsInMinutes(durationSong);
    AlbumOfTrack.CoverArt coverArt = trackItem.getAlbumOfTrack().getCoverArt();
    String urlImage = coverArt.getSources()[0].getUrl();
    new DownloadImageTask(holder.songImage).execute(urlImage);
    String profileNameArtist = trackItem.getArtists().getItems()[0].getProfile().getName();

    holder.songName.setText(nameSong);
    holder.artist.setText(nameSong);
    holder.duration.setText(duration);
    holder.artist.setText(profileNameArtist);
  }

  @Override
  public int getItemCount() {
    int totalItems = trackList == null ? 0 : trackList.size();
    return totalItems;
  }

  public class TrackViewHolder extends RecyclerView.ViewHolder {

    private TextView artist;
    private TextView songName;
    private ImageView songImage;
    private TextView duration;

    public TrackViewHolder(@NonNull View itemView) {
      super(itemView);

      songName = itemView.findViewById(R.id.songNameItem);
      artist = itemView.findViewById(R.id.artistNameItem);
      songImage = itemView.findViewById(R.id.songImageItem);
      duration = itemView.findViewById(R.id.songDurationItem);

    }
  }
}
