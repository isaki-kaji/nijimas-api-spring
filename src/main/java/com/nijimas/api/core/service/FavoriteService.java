package com.nijimas.api.core.service;

import com.nijimas.api.application.favorite.ToggleParam;
import com.nijimas.api.core.constant.FavoriteStatusConstants;

public interface FavoriteService {

    FavoriteStatusConstants toggleFavorite(ToggleParam param);
}
