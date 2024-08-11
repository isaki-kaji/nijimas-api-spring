package com.nijimas.api.core.service;

import com.nijimas.api.application.favorite.ToggleFavoriteParam;
import com.nijimas.api.core.constant.FavoriteStatus;

public interface FavoriteService {

    FavoriteStatus toggleFavorite(ToggleFavoriteParam param);
}
