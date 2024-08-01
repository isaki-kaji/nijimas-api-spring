package com.nijimas.api.core.service;

import com.nijimas.api.application.favorite.ToggleParam;
import com.nijimas.api.core.constant.FavoriteStatus;

public interface FavoriteService {

    FavoriteStatus toggleFavorite(ToggleParam param);
}
