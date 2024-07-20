package com.nijimas.api.application.favorite;

import com.nijimas.api.core.entity.FavoriteEntity;
import com.nijimas.api.core.repository.FavoriteRepository;
import com.nijimas.api.core.service.FavoriteService;
import com.nijimas.api.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Override
    public boolean toggleFavorite(ToggleParam param) {
        UUID postId = CommonUtil.parseUuid(param.getPostId());
        FavoriteEntity favorite = new FavoriteEntity(postId, param.getUid());
        boolean hasCreated = true;

        if (favoriteRepository.find(postId, param.getUid()).isEmpty()) {
            favoriteRepository.save(favorite);
            return hasCreated;
        }
        favoriteRepository.delete(favorite);
        return !hasCreated;
    }
}
