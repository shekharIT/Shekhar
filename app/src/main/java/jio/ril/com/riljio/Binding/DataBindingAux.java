/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jio.ril.com.riljio.Binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by chkumar on 11/22/17.
 * Data Binding Adapters
 */
public class DataBindingAux {

    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView v, String imgUrl) {

        Glide.with(v.getContext())
                .load(imgUrl).apply(RequestOptions.circleCropTransform())
                .into(v);
    }

}

