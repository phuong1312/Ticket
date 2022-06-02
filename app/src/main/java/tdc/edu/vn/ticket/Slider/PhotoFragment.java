package tdc.edu.vn.ticket.Slider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import tdc.edu.vn.ticket.R;

public class PhotoFragment extends Fragment {
    private View view;
    private ImageView imgPhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photo_layout, container, false);

        Bundle bundle = getArguments();
        Photo photo = (Photo) bundle.get("object_photo");

        imgPhoto = view.findViewById(R.id.img_photo);
        imgPhoto.setImageResource(photo.getResourceId());

        return view;
    }
}
