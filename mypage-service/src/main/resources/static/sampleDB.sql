use bit;

insert into country_visit (user_id, country_id)
VALUES
('bbb', 'KOR'),
('bbb', 'FRA'),
('bbb', 'CHN'),
('bbb', 'USA'),
('bbb', 'BEL'),
('bbb', 'DNK'),
('bbb', 'CHE'),
('bbb', 'ITA'),
('bbb', 'TUR'),
('bbb', 'ARE'),
('bbb', 'CAN'),
('bbb', 'DEU'),
('bbb', 'TWN'),
('bbb', 'ESP'),
('bbb', 'PRT'),
('bbb', 'LUX');

insert into country_wish (user_id, country_id)
VALUES 
('bbb', 'HRV'),
('bbb', 'PRK'),
('bbb', 'THA'),
('bbb', 'MYS'),
('bbb', 'RUS'),
('bbb', 'GBR'),
('bbb', 'AFG'),
('bbb', 'AGO'),
('bbb', 'ALB'),
('bbb', 'AND'),
('bbb', 'ANT'),
('bbb', 'ARG'),
('bbb', 'BDI'),
('bbb', 'BEN'),
('bbb', 'BFA'),
('bbb', 'BGD'),
('bbb', 'BGR');
/* Proficiency: LOW, INTERMEDIATE, HIGH, NATIVE */
INSERT INTO language_user (user_id, language_id, proficiency)
VALUES 
('bbb', 23, 'INTERMEDIATE'),
('bbb', 206, 'NATIVE'),
('bbb', 2, 'INTERMEDIATE'),
('bbb', 79, 'INTERMEDIATE'),
('bbb', 4, 'LOW');

insert into country_fav (user_id, country_id, country_rank)
VALUES
('bbb', 'KOR', 1),
('bbb', 'TWN', 3),
('bbb', 'ESP', 2),
('bbb', 'PRT', 4),
('bbb', 'LUX', 5);


ALTER TABLE user
MODIFY COLUMN map_color enum('GREEN', 'BLUE', 'PINK', 'YELLOW') NOT NULL DEFAULT 'BLUE';


insert into bucketlist (user_id, title, description, display_order)
VALUES
('bbb', 'title 1 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 1),
('bbb', 'title 2 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 2),
('bbb', 'title 3 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 3),
('bbb', 'title 4 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 5),
('bbb', 'title 5 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 4),
('bbb', 'title 6 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 6),
('bbb', 'title 7 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 7),
('bbb', 'title 8 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 8)
;

insert into bucketlist (user_id, title, description, display_order, completion_status)
VALUES
('bbb', 'title 1 자미틱 던앵는 가미다겐 아크샌지가 히닏극어건 딜실묭으로서', '븡즈디킬 뢴니아설에서 허액며를 정나모허어, 르참얼은 뤠시어야지 일를인민싱 컴드홍억습니다. 잰간 래리간겡도 면연도시나 뵤닸 린태랏. 수하애의 허악으란다 르로소는 인노를 라석에서 늑과녹으로 라가티어요 에죅조으다', 9, TRUE);


ALTER TABLE feed_post
MODIFY COLUMN title TEXT; 

INSERT INTO feed_post (user_id, country_id, city_id, title)
VALUES
('bbb', 'KOR', 2331, '오랜만에 서울'),
('bbb', 'FRA', 2974, '몽마르트 언덕 근처 카페에서 에스프레소를 마시며 파리의 흐린 하늘을 바라보았습니다.'),
('bbb', 'TUR', 3357, '보스포루스 해협에서 해질녘 들려오는 아잔 소리가 도시의 분위기를 더해주네요.'),
('bbb', 'CAN', 3939, '바다 옆 산책로를 따라 걸으며 노을에 물든 산의 모습을 감상했습니다.'),
('bbb', 'ITA', 1464, '트레비 분수에 동전을 던지고 돌아오는 길에 피스타치오 젤라또를 먹었습니다.'),
('bbb', 'ISL', 1449, '별이 가득한 밤하늘 아래에서 오로라를 기다리며 담요를 덮고 앉아 있었습니다.'),
('bbb', 'MAR', 2487, '다채로운 수크(시장)를 거닐며 민트차를 마시고 향신료를 구경했습니다.'),
('bbb', 'KOR', 2331, '청계천을 따라 밤 산책을 하며 반짝이는 네온사인을 구경했습니다.'),
('bbb', 'ESP', 654, '옥상 테라스에서 따뜻한 바람을 맞으며 타파스를 즐긴 하루였습니다.'),
('bbb', 'AUS', 130, '본다이 비치에서 서핑하는 사람들을 보며 아침 커피를 마셨습니다.');

INSERT INTO feed_comment (feed_id, user_id, parent_comment_id, content) VALUES
(1, 'aaa', NULL, '사진이 정말 예쁘네요! 저도 가보고 싶어요.'),
(1, 'ccc', NULL, '여기 가려면 언제가 제일 좋은가요?'),
(1, 'bbb', 2, '봄이랑 가을이 가장 좋아요! 사람이 적고 날씨도 좋아요.'),
(1, 'ccc', NULL, '저도 파리 가서 카페에서 앉아 있고 싶네요.'),
(1, 'ddd', NULL, '분위기가 너무 좋다...'),
(1, 'eee', NULL, '이스탄불의 노을은 정말 아름답죠.'),
(1, 'bbb', 2, '맞아요! 저녁이 되면 색이 정말 예뻐요.'),
(1, 'eee', NULL, '사진만 봐도 마음이 편해지네요.'),
(1, 'aaa', NULL, '젤라또 맛있었나요? ㅎㅎ'),
(1, 'bbb', 2, '피스타치오 맛이 정말 고소하고 맛있었어요!'),
(1, 'fff', 5, '오로라 진짜 직접 보고 싶어요.'),
(1, 'fff', 5, '모로코 시장 구경 재밌죠!'),
(1, 'ddd', 5, '서울 야경 진짜 멋있어요.'),
(1, 'ccc', 5, '타파스는 어떤 게 제일 맛있었나요?'),
(1, 'bbb', 2, '감바스랑 빠에야가 가장 맛있었어요!'),
(1, 'eee', NULL, '서핑하는 사람들 보면서 커피라니... 너무 멋져요.');


INSERT INTO feed_image (feed_id, photo_url, display_order) VALUES
(1, 'https://i.pinimg.com/736x/83/89/52/83895264750bf768114d2c65459641db.jpg', 1), /*korea*/
(2, 'https://i.pinimg.com/736x/9d/c9/ae/9dc9aec42a5ca2b933ec90a8fb1eb8c4.jpg', 1),
(1, 'https://i.pinimg.com/736x/e9/dc/d8/e9dcd8642209aa8dfd5ab8fb49286e15.jpg', 1),
(1, 'https://i.pinimg.com/736x/52/45/9e/52459e278fafb10d3a7f48e8606e8ee7.jpg', 1),
(1, 'https://i.pinimg.com/736x/00/08/3c/00083cc80645e084d385088c578cd30c.jpg', 1),
(1, 'https://i.pinimg.com/736x/a7/2f/4f/a72f4ffa0d5b8d7aff33b63ec37f4e4e.jpg', 1),
(2, 'https://i.pinimg.com/736x/12/ad/5d/12ad5dc85d97209bb4d3f24d19864a4c.jpg', 1), /*france*/
(2, 'https://i.pinimg.com/736x/35/da/f2/35daf21772c0923e80d80e8d2461a4db.jpg', 1), /*france*/
(3, 'https://i.pinimg.com/736x/ae/09/b6/ae09b66ba6f2c97b70b40661be900817.jpg', 1), /*turkey*/
(4, 'https://i.pinimg.com/736x/51/a7/51/51a751f51df9896e4605135c4b0121d0.jpg', 1),
(5, 'https://i.pinimg.com/736x/94/a8/73/94a873b4b35bece7f257d467a605474f.jpg', 1),
(6, 'https://i.pinimg.com/736x/63/cc/4e/63cc4e3af4b264f65940776b63fcec1e.jpg', 1),
(7, 'https://i.pinimg.com/736x/32/00/55/3200554b45f388dde67ec2d2ad50dcc0.jpg', 1),
(8, 'https://i.pinimg.com/736x/81/56/6c/81566c984cecd927d9d0752aeef582db.jpg', 1),
(9, 'https://i.pinimg.com/736x/d3/be/c9/d3bec943f8a1e8b57d6e51d24c003a0d.jpg', 1),
(10, 'https://i.pinimg.com/736x/4c/49/75/4c497530e4aaca38ef7da0855cb3f58c.jpg', 1);

INSERT INTO feed_post (user_id, country_id, city_id, title)
VALUES
('bbb', 'KOR', 2331, '오랜만에 서울 #여행 #먹거리'),
('bbb', 'FRA', 2974, '몽마르트 언덕 근처 카페에서 에스프레소를 마시며 파리의 흐린 하늘을 바라보았습니다. #여행 #먹거리'),
('bbb', 'TUR', 3357, '보스포루스 해협에서 해질녘 들려오는 아잔 소리가 도시의 분위기를 더해주네요. #여행'),
('bbb', 'CAN', 3939, '바다 옆 산책로를 따라 걸으며 노을에 물든 산의 모습을 감상했습니다. #여행'),
('bbb', 'ITA', 1464, '트레비 분수에 동전을 던지고 돌아오는 길에 피스타치오 젤라또를 먹었습니다. #여행'),
('bbb', 'ISL', 1449, '별이 가득한 밤하늘 아래에서 오로라를 기다리며 담요를 덮고 앉아 있었습니다. #여행'),
('bbb', 'MAR', 2487, '다채로운 수크(시장)를 거닐며 민트차를 마시고 향신료를 구경했습니다. #여행'),
('bbb', 'KOR', 2331, '청계천을 따라 밤 산책을 하며 반짝이는 네온사인을 구경했습니다. #여행'),
('bbb', 'ESP', 654, '옥상 테라스에서 따뜻한 바람을 맞으며 타파스를 즐긴 하루였습니다. #여행'),
('bbb', 'AUS', 130, '본다이 비치에서 서핑하는 사람들을 보며 아침 커피를 마셨습니다. #여행');

insert into feed_hashtag (feed_id, hashtag_id) values 
(39, 16),
(40, 16);

INSERT INTO feed_image (feed_id, photo_url, display_order) VALUES
(39, 'https://i.pinimg.com/736x/83/89/52/83895264750bf768114d2c65459641db.jpg', 1),
(40, 'https://i.pinimg.com/1200x/92/f8/93/92f8938c67c364c0b9d904e4611c3da7.jpg', 1),
(41, 'https://i.pinimg.com/1200x/7f/a3/bd/7fa3bd5ae7c6c6436fc74f5f02792c9a.jpg', 1),
(42, 'https://i.pinimg.com/736x/d0/a8/bc/d0a8bc8052b6d1e2f5355a39a0ec4835.jpg', 1),
(43, 'https://i.pinimg.com/736x/48/b8/79/48b879166473e5617396beab3767c40e.jpg', 1),
(44, 'https://i.pinimg.com/736x/80/97/63/8097634f1268fbc8a0cfe88ab4ceaac8.jpg', 1),
(45, 'https://i.pinimg.com/736x/3d/ff/b1/3dffb1f831f415e1c0141d272b5321f7.jpg', 1),
(46, 'https://i.pinimg.com/736x/73/3c/cc/733ccc53fd193bbe8eae70588ced36a4.jpg', 1),
(47, 'https://i.pinimg.com/1200x/ee/b6/18/eeb618a1025171ef8c6ff10c7b77c1f1.jpg', 1),
(48, 'https://i.pinimg.com/736x/a1/f1/69/a1f169ad0e2e541d1bb027b0145eb8f0.jpg', 1),
(49, 'https://i.pinimg.com/1200x/52/05/3e/52053e9251316afd96cbfd30b188994c.jpg', 1),
(50, 'https://i.pinimg.com/1200x/45/bd/a1/45bda16f28cbb44f6a53db7bf116c3ab.jpg', 1),
(51, 'https://i.pinimg.com/736x/39/48/e5/3948e56a0107ebd48e0617aa6de8ce5a.jpg', 1),
(52, 'https://i.pinimg.com/736x/4a/70/74/4a7074e01ac804231a3956c5933cd106.jpg', 1),
(53, 'https://i.pinimg.com/736x/f3/50/16/f3501627391116affcffaaecd7c3ae2a.jpg', 1),
(54, 'https://i.pinimg.com/1200x/9e/46/0e/9e460ea01004cb8422c230945d509279.jpg', 1),
(55, 'https://i.pinimg.com/736x/b6/34/3b/b6343bcc49f5bfed1a08d837f6f425dd.jpg', 1),
(56, 'https://i.pinimg.com/1200x/28/bb/15/28bb15db7bcea22746521bb5c3fd803a.jpg', 1),
(57, 'https://i.pinimg.com/1200x/95/de/95/95de956ca1abc82350ad6384c5f79c9e.jpg', 1),
(58, 'https://i.pinimg.com/736x/48/77/19/487719d760a8d8287e98709cfc14cbbd.jpg', 1);

CREATE TABLE feed_comment_like (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    comment_id INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    liked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_feed_comment_user (comment_id, user_id),
    FOREIGN KEY (comment_id) REFERENCES feed_comment(id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

ALTER TABLE feed_comment
DROP FOREIGN KEY feed_comment_ibfk_3;

ALTER TABLE feed_comment
ADD CONSTRAINT feed_comment_ibfk_3
FOREIGN KEY (parent_comment_id) REFERENCES feed_comment(id)
ON DELETE CASCADE;

ALTER TABLE hashtag ADD COLUMN count INT NOT NULL DEFAULT 0;

insert into feed_comment_like (comment_id, user_id) VALUES 
(1, 'bbb'),
(1, 'aaa'),
(1, 'ccc'),
(2, 'bbb'),
(2, 'aaa');
insert into follow (follower_id, following_id) VALUES
('jigubang', 'aaa'),
('jigubang', 'ccc'),
('jigubang', 'ddd'),
('jigubang', 'eee'),
('jigubang', 'fff'),
('jigubang', 'ggg'),
('jigubang', 'hhh'),
('jigubang', 'iii'),
('jigubang', 'jjj'),
('jigubang', 'kkk'),
('jigubang', 'lll'),
('jigubang', 'mmm'),
('jigubang', 'nnn'),
('jigubang', 'ooo'),
('jigubang', 'ppp'),
('jigubang', 'qqq'),
('jigubang', 'rrr'),
('jigubang', 'sss'),
('jigubang', 'ttt'),
('jigubang', 'uuu'),
('jigubang', 'vvv'),
('jigubang', 'www'),
('jigubang', 'xxx'),
('jigubang', 'yyy'),
('jigubang', 'zzz');

insert into follow (follower_id, following_id) VALUES
('aaa', 'jigubang'),
('ccc', 'jigubang'),
('ddd', 'jigubang'),
('eee', 'jigubang'),
('fff', 'jigubang'),
('ggg', 'jigubang'),
('hhh', 'jigubang'),
('iii', 'jigubang'),
('jjj', 'jigubang'),
('kkk', 'jigubang'),
('lll', 'jigubang'),
('mmm', 'jigubang'),
('nnn', 'jigubang'),
('ooo', 'jigubang'),
('ppp', 'jigubang'),
('qqq', 'jigubang'),
('rrr', 'jigubang'),
('sss', 'jigubang'),
('ttt', 'jigubang'),
('uuu', 'jigubang'),
('vvv', 'jigubang'),
('www', 'jigubang'),
('xxx', 'jigubang');


INSERT INTO feed_post (user_id, country_id, city_id, title)
VALUES
('bbb', 'KOR', 2331, '오랜만에 서울 #여행 #먹거리'),
('bbb', 'FRA', 2974, '몽마르트 언덕 근처 카페에서 에스프레소를 마시며 파리의 흐린 하늘을 바라보았습니다. #여행 #먹거리'),
('bbb', 'TUR', 3357, '보스포루스 해협에서 해질녘 들려오는 아잔 소리가 도시의 분위기를 더해주네요. #먹거리'),
('bbb', 'CAN', 3939, '바다 옆 산책로를 따라 걸으며 노을에 물든 산의 모습을 감상했습니다. #먹거리'),
('bbb', 'ITA', 1464, '트레비 분수에 동전을 던지고 돌아오는 길에 피스타치오 젤라또를 먹었습니다. #먹거리'),
('bbb', 'ISL', 1449, '별이 가득한 밤하늘 아래에서 오로라를 기다리며 담요를 덮고 앉아 있었습니다. #먹거리'),
('bbb', 'MAR', 2487, '다채로운 수크(시장)를 거닐며 민트차를 마시고 향신료를 구경했습니다. #먹거리'),
('bbb', 'KOR', 2331, '청계천을 따라 밤 산책을 하며 반짝이는 네온사인을 구경했습니다. #먹거리'),
('bbb', 'ESP', 654, '옥상 테라스에서 따뜻한 바람을 맞으며 타파스를 즐긴 하루였습니다. #먹거리'),
('bbb', 'AUS', 130, '본다이 비치에서 서핑하는 사람들을 보며 아침 커피를 마셨습니다. #먹거리');

describe feed_hashtag;
/* #먹거리 에 피드 추가*/
insert into feed_hashtag (feed_id, hashtag_id) values 
(49, 16),
(50, 16),
(51, 16),
(52, 16),
(53, 16),
(54, 16),
(55, 16),
(56, 16),
(57, 16),
(58, 16)
;

select * from hashtag;
select * from feed_hashtag;
select * from feed_post;
INSERT INTO feed_image (feed_id, photo_url, display_order) VALUES
(49, 'https://i.pinimg.com/1200x/52/05/3e/52053e9251316afd96cbfd30b188994c.jpg', 1),
(50, 'https://i.pinimg.com/1200x/45/bd/a1/45bda16f28cbb44f6a53db7bf116c3ab.jpg', 1),
(51, 'https://i.pinimg.com/736x/39/48/e5/3948e56a0107ebd48e0617aa6de8ce5a.jpg', 1),
(52, 'https://i.pinimg.com/736x/4a/70/74/4a7074e01ac804231a3956c5933cd106.jpg', 1),
(53, 'https://i.pinimg.com/736x/f3/50/16/f3501627391116affcffaaecd7c3ae2a.jpg', 1),
(54, 'https://i.pinimg.com/1200x/9e/46/0e/9e460ea01004cb8422c230945d509279.jpg', 1),
(55, 'https://i.pinimg.com/736x/b6/34/3b/b6343bcc49f5bfed1a08d837f6f425dd.jpg', 1),
(56, 'https://i.pinimg.com/1200x/28/bb/15/28bb15db7bcea22746521bb5c3fd803a.jpg', 1),
(57, 'https://i.pinimg.com/1200x/95/de/95/95de956ca1abc82350ad6384c5f79c9e.jpg', 1),
(58, 'https://i.pinimg.com/736x/48/77/19/487719d760a8d8287e98709cfc14cbbd.jpg', 1);

INSERT INTO style_test_question (content) VALUES
('여행을 떠나볼까? 어디로 갈지 먼저 생각해봐야지!'),
('가고 싶은 나라가 몇 군데 떠오르네! 이제 여행 방식을 정해볼까?'),
('그런데... 여행은 혼자 가는 걸까?'),
('벌써 밥 시간이네! 그런데 여행 가서 뭘 먹지?'),
('이건 꼭 먹어야겠어! 이제 여행 중에 뭘 할지도 정해야지.'),
('이 활동도 좋고, 그 다음날엔 뭐하지?'),
('좋아, 도시도 정했고 비행기표도 예매했어! 이제 숙소를 고르자.'),
('마음에 드는 숙소를 골랐어! 그런데 숙소까지는 어떻게 가지?'),
('이제 하고 싶은 것도 정했고, 예약만 남았어.'),
('숙소 주변을 보니까 가보고 싶은 곳이 꽤 있네! 여기는 일정 중간에 넣자.'),
('여기는 거리가 생각보다 있네. 한 20분 정도 걸린대.'),
('마지막 날엔 쇼핑도 해야지!'),
('그런데 쇼핑은 뭘 사야 할까?'),
('대략적인 계획은 다 세웠다! 이제 여행을 상상하니 설레는걸!'),
('여행 가서 SNS는 어떻게 하지?');

INSERT INTO style_test_option (question_id, option_type, content) VALUES
(1, 1, '따뜻한 나라에서 여름 분위기를 느끼며 해변을 거닐고 싶어!'),
(1, 2, '더운 건 딱 질색! 선선하거나 쌀쌀한 나라로 떠나고 싶어.'),
(2, 1, '전문가에게 맡기는 게 편할 것 같아. 미리 짜여진 패키지나 투어를 알아볼래.'),
(2, 2, '자유로운 여행이 최고지! 이번엔 어떤 도시부터 둘러볼까?'),
(3, 1, '친구나 가족이랑 함께 가야지~ 내 계획에 잘 어울릴 사람을 떠올려본다.'),
(3, 2, '혼자 떠나는 여행도 좋아! 자유롭게 다니고 지금 바로 비행기랑 숙소부터 예약해야지~'),
(4, 1, '현지 음식이 최고지! 그 나라의 맛을 느낄 수 있는 로컬 푸드를 먹을래.'),
(4, 2, '입맛에 안 맞으면 곤란하니까... 익숙하고 안전한 메뉴 위주로 먹을래.'),
(5, 1, '유명한 산이나 바다! 등산, 다이빙 같은 액티비티를 해보고 싶어!'),
(5, 2, '아늑한 카페나 잔잔한 산책, 따뜻한 온천처럼 조용한 활동이 좋아.'),
(6, 1, '그 나라 문화를 느끼고 싶어. 미술관이나 박물관, 역사 유적지를 방문해볼래.'),
(6, 2, '나는 초록이 좋아! 자연 경관을 즐기면서 트레킹 같은 힐링 여행을 하고 싶어.'),
(7, 1, '여행 중에는 잘 자는 게 중요하지! 고급 호텔이나 리조트로 예약할래.'),
(7, 2, '현지 감성 제대로 느끼려면 게스트하우스나 에어비앤비가 딱이지!'),
(8, 1, '렌트카로 자유롭게 다닐래! 대중교통이 없는 곳도 마음껏 갈 수 있잖아.'),
(8, 2, '현지 분위기를 느끼려면 역시 대중교통이지! 루트부터 차근차근 알아봐야지.'),
(9, 1, '여기가 마음에 쏙 들어! 언어가 안 통해도 번역기가 있으니 문제없어. 도전!'),
(9, 2, '그래도 기본적인 영어, 한국어는 통해야지. 언어가 통하는 곳으로 예약할래.'),
(10, 1, '근처에 유명 맛집이 있다던데! 웨이팅이 좀 있어도 꼭 가보고 싶어.'),
(10, 2, '조금만 걸어가면 조용한 골목이 있대. 로컬 감성이 가득하다던데 꼭 가봐야지.'),
(11, 1, '여행 왔으면 하루에 2~3만 보쯤은 걸어야지! 20분 정도야 금방이지.'),
(11, 2, '너무 많이 걷는 건 피하고 싶어. 다른 일정도 있으니까 여긴 걷지 말고 이동수단을 써야겠어.'),
(12, 1, '여기 현지 브랜드가 그렇게 유명하대! 기념품도 챙기고 쇼핑몰 투어는 필수야!'),
(12, 2, '그냥 둘러보다가 괜찮은 게 있으면 간단히 살래. 필요한 것만 딱!'),
(13, 1, '친구들이랑 부모님 선물은 꼭 사야지! 여긴 이게 유명하다던데, 이거 좋아 보인다!'),
(13, 2, '잘 다녀오는 게 가장 중요하지. 좋은 추억만 있으면 돼. 꼭 선물은 안 사도 괜찮아.'),
(14, 1, '사진은 꼭 많이 찍어야지! 여행 일기장도 준비해야겠어.'),
(14, 2, '사진보다 내 눈으로 풍경과 분위기를 느끼고 싶어!'),
(15, 1, '보조배터리는 필수! 실시간으로 SNS에 올리면서 여행을 공유할 거야.'),
(15, 2, '다녀와서 한 번에 정리해서 올릴래. 아니면 굳이 안 올려도 되지~ 내 기억이면 충분해.');