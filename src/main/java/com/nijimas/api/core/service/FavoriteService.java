package com.nijimas.api.core.service;

import com.nijimas.api.application.favorite.ToggleParam;

public interface FavoriteService {

    boolean toggleFavorite(ToggleParam param);
}
