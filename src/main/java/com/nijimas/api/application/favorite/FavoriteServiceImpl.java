package com.nijimas.api.application.favorite;

import com.nijimas.api.core.constant.FavoriteStatus;
import com.nijimas.api.core.entity.FavoriteEntity;
import com.nijimas.api.core.exception.post.PostNotFoundException;
import com.nijimas.api.core.repository.FavoriteRepository;
import com.nijimas.api.core.repository.PostRepository;
import com.nijimas.api.core.service.FavoriteService;
import com.nijimas.api.util.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final PostRepository postRepository;

    @Override
    public FavoriteStatus toggleFavorite(ToggleParam param) {

        UUID postId = CommonUtil.parseUuid(param.getPostId());

        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId);
        }

        FavoriteEntity favorite = new FavoriteEntity(postId, param.getUid());

        if (!favoriteRepository.existsById(favorite)) {
            favoriteRepository.save(favorite);
            return FavoriteStatus.CREATED;
        }
        favoriteRepository.delete(favorite);
        return FavoriteStatus.DELETED;
    }
}
