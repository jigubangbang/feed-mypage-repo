package com.jigubangbang.mypage_service.model.chat_service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedNotificationRequestDto {

    private String authorId;                //userId
    private int feedId;                     //relatedId
    private String relatedUrl;
    private String senderId;
    private String senderProfileImage;

}
