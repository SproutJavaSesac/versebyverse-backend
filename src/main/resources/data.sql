-- === Member 10명 생성 (반드시 Post보다 먼저 실행되어야 합니다) ===
-- RoleType: ROLE_USER, ROLE_ADMIN
-- SocialType: KAKAO, GOOGLE
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (1, 'ROLE_USER', 'KAKAO', 'sesac1@gmail.com', '행복한 코알라', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (2, 'ROLE_USER', 'GOOGLE', 'sesac2@gmail.com', '슬기로운 미어캣', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (3, 'ROLE_USER', 'KAKAO', 'sesac3@gmail.com', '용감한 펭귄', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (4, 'ROLE_USER', 'GOOGLE', 'sesac4@gmail.com', '신비로운 유니콘', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (5, 'ROLE_USER', 'KAKAO', 'sesac5@gmail.com', '춤추는 알파카', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (6, 'ROLE_USER', 'GOOGLE', 'sesac6@gmail.com', '고요한 나무늘보', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (7, 'ROLE_USER', 'KAKAO', 'sesac7@gmail.com', '씩씩한 햄스터', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (8, 'ROLE_USER', 'GOOGLE', 'sesac8@gmail.com', '자유로운 돌고래', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (9, 'ROLE_USER', 'KAKAO', 'sesac9@gmail.com', '어쩌구 저쩌구', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (10, 'ROLE_USER', 'GOOGLE', 'sesac10@gmail.com', '명상하는 여우', NOW(), NOW());
INSERT INTO members (id, role_type, social_type, email, nickname, created_at, updated_at) VALUES (11, 'ROLE_ADMIN', 'KAKAO', 'sesac11@gmail.com', '관리자쿼카', NOW(), NOW());

-- === Post 200개 생성 (회원별 개수 차등) ===
-- Post의 author_id는 위에서 생성한 Member의 id를 참조합니다.
-- Emotion: HAPPY, SAD, ANGRY, EXCITED, CONFUSED, PROUD
-- Concept: CLASSICAL_POETRY, POETRY, NOVEL, DRAMA, ESSAY

-- Member 1 (author_id=1): 10개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (1, 1, '오늘 정말 행복한 하루였어요. 수정 전 내용.', '오늘 정말 행복한 하루였습니다. AI가 다듬은 내용.', '행복한 날', '행복했던 하루', 'https://placehold.co/600x400?text=Post+1', false, false, false, 'HAPPY', 'ESSAY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (2, 1, '길을 잃어서 슬펐어요.', '길을 잃어 잠시 슬픔에 잠겼습니다.', '슬픈 날', '길 잃은 마음', 'https://placehold.co/600x400?text=Post+2', false, false, false, 'SAD', 'POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (3, 1, '버스가 눈앞에서 지나가서 화가 났어요.', '버스가 눈앞에서 지나가 화가 치밀어 올랐습니다.', '화나는 날', '놓친 버스와 분노', 'https://placehold.co/600x400?text=Post+3', false, false, false, 'ANGRY', 'DRAMA', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (4, 1, '선물 받아서 신나요!', '생각지도 못한 선물에 마음이 들뜹니다!', '신나는 날', '예상치 못한 기쁨', 'https://placehold.co/600x400?text=Post+4', false, false, false, 'EXCITED', 'NOVEL', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (5, 1, '이게 맞나? 혼란스러워요.', '무엇이 옳은 길인지 혼란스럽습니다.', '혼란스러운 날', '갈래길에서의 고민', 'https://placehold.co/600x400?text=Post+5', false, false, false, 'CONFUSED', 'CLASSICAL_POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (6, 1, '스스로가 자랑스러워요.', '오늘의 성취가 저를 자랑스럽게 만듭니다.', '자랑스러운 날', '성취감의 기록', 'https://placehold.co/600x400?text=Post+6', false, false, false, 'PROUD', 'ESSAY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (7, 1, '행복한 하루의 기록', '행복으로 가득했던 하루를 기록합니다.', '행복 기록', '따스한 하루의 추억', 'https://placehold.co/600x400?text=Post+7', false, false, false, 'HAPPY', 'POETRY', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (8, 1, '옛날 생각이 나서 슬퍼요.', '문득 떠오른 옛 생각에 마음이 아려옵니다.', '추억과 슬픔', '지나간 시간의 그리움', 'https://placehold.co/600x400?text=Post+8', false, false, false, 'SAD', 'DRAMA', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (9, 1, '부당한 일에 화가 나요.', '세상의 부당함에 분노를 느낍니다.', '정의로운 분노', '불의에 맞서는 마음', 'https://placehold.co/600x400?text=Post+9', false, false, false, 'ANGRY', 'NOVEL', NOW(), NOW());
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) VALUES (10, 1, '내일 소풍가서 신나요.', '내일 있을 소풍 생각에 가슴이 뜁니다.', '소풍 전날', '설레는 마음의 기대', 'https://placehold.co/600x400?text=Post+10', false, false, false, 'EXCITED', 'CLASSICAL_POETRY', NOW(), NOW());

-- Member 2 (author_id=2): 12개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 10+n, 2, concat('내용 ', 10+n), concat('수정된 내용 ', 10+n), concat('제목 ', 10+n), concat('다듬어진 제목 ', 10+n), concat('https://placehold.co/600x400?text=Post+', 10+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 12;

-- Member 3 (author_id=3): 14개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 22+n, 3, concat('내용 ', 22+n), concat('수정된 내용 ', 22+n), concat('제목 ', 22+n), concat('다듬어진 제목 ', 22+n), concat('https://placehold.co/600x400?text=Post+', 22+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 14;

-- Member 4 (author_id=4): 20개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 36+n, 4, concat('내용 ', 36+n), concat('수정된 내용 ', 36+n), concat('제목 ', 36+n), concat('다듬어진 제목 ', 36+n), concat('https://placehold.co/600x400?text=Post+', 36+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 20;

-- Member 5 (author_id=5): 22개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 56+n, 5, concat('내용 ', 56+n), concat('수정된 내용 ', 56+n), concat('제목 ', 56+n), concat('다듬어진 제목 ', 56+n), concat('https://placehold.co/600x400?text=Post+', 56+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 22;

-- Member 6 (author_id=6): 25개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 78+n, 6, concat('내용 ', 78+n), concat('수정된 내용 ', 78+n), concat('제목 ', 78+n), concat('다듬어진 제목 ', 78+n), concat('https://placehold.co/600x400?text=Post+', 78+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 25;

-- Member 7 (author_id=7): 30개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 103+n, 7, concat('내용 ', 103+n), concat('수정된 내용 ', 103+n), concat('제목 ', 103+n), concat('다듬어진 제목 ', 103+n), concat('https://placehold.co/600x400?text=Post+', 103+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2) n10) nums WHERE n BETWEEN 1 AND 30;

-- Member 8 (author_id=8): 37개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 133+n, 8, concat('내용 ', 133+n), concat('수정된 내용 ', 133+n), concat('제목 ', 133+n), concat('다듬어진 제목 ', 133+n), concat('https://placehold.co/600x400?text=Post+', 133+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) n10) nums WHERE n BETWEEN 1 AND 37;

-- Member 9 (author_id=9): 15개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 170+n, 9, concat('내용 ', 170+n), concat('수정된 내용 ', 170+n), concat('제목 ', 170+n), concat('다듬어진 제목 ', 170+n), concat('https://placehold.co/600x400?text=Post+', 170+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 15;

-- Member 10 (author_id=10): 15개
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at) SELECT 185+n, 10, concat('내용 ', 185+n), concat('수정된 내용 ', 185+n), concat('제목 ', 185+n), concat('다듬어진 제목 ', 185+n), concat('https://placehold.co/600x400?text=Post+', 185+n), false, false, false, ELT(MOD(n,6)+1, 'HAPPY','SAD','ANGRY','EXCITED','CONFUSED','PROUD'), ELT(MOD(n,5)+1, 'CLASSICAL_POETRY','POETRY','NOVEL','DRAMA','ESSAY'), NOW(), NOW() FROM (SELECT (n1.n + n10.n*10) as n FROM (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1, (SELECT 0 as n UNION ALL SELECT 1) n10) nums WHERE n BETWEEN 1 AND 15;


-- === 동적 날짜의 Post 200개 추가 생성 ===
-- 실행 시점 기준으로 어제와 그제 날짜의 데이터가 생성됩니다.
-- 이 데이터는 이전에 생성한 데이터와는 별개입니다. ID는 201부터 시작합니다.

-- 그제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 2 DAY) : 실행일 기준 그제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at)
SELECT
    200 + n, -- id
    FLOOR(1 + RAND() * 10), -- random author_id (1~10)
    CONCAT('그제 내용 ', 200 + n),
    CONCAT('수정된 그제 내용 ', 200 + n),
    CONCAT('그제 제목 ', 200 + n),
    CONCAT('다듬어진 그제 제목 ', 200 + n),
    CONCAT('https://placehold.co/600x400?text=Post+', 200 + n),
    false, false, false,
    ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
    ELT(MOD(n, 5) + 1, 'CLASSICAL_POETRY', 'POETRY', 'NOVEL', 'DRAMA', 'ESSAY'),
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND,
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 2 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM
    (SELECT (n1.n + n10.n*10) as n FROM
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1,
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n10
    ) nums
WHERE n BETWEEN 1 AND 100;


-- 어제 날짜의 게시글 100개
-- DATE_SUB(CURDATE(), INTERVAL 1 DAY) : 실행일 기준 어제 날짜
INSERT INTO posts(id, author_id, before_content, after_content, before_title, after_title, image_url, is_reported, is_deleted, is_hidden, emotion_type, concept_type, created_at, updated_at)
SELECT
    300 + n, -- id
    FLOOR(1 + RAND() * 10), -- random author_id (1~10)
    CONCAT('어제 내용 ', 300 + n),
    CONCAT('수정된 어제 내용 ', 300 + n),
    CONCAT('어제 제목 ', 300 + n),
    CONCAT('다듬어진 어제 제목 ', 300 + n),
    CONCAT('https://placehold.co/600x400?text=Post+', 300 + n),
    false, false, false,
    ELT(MOD(n, 6) + 1, 'HAPPY', 'SAD', 'ANGRY', 'EXCITED', 'CONFUSED', 'PROUD'),
    ELT(MOD(n, 5) + 1, 'CLASSICAL_POETRY', 'POETRY', 'NOVEL', 'DRAMA', 'ESSAY'),
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND,
    TIMESTAMP(DATE_SUB(CURDATE(), INTERVAL 1 DAY)) + INTERVAL FLOOR(RAND() * 86400) SECOND
FROM
    (SELECT (n1.n + n10.n*10) as n FROM
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n1,
                                       (SELECT 0 as n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) n10
    ) nums
WHERE n BETWEEN 1 AND 100;

-- === 계층형 댓글 더미 데이터 200개 생성 (is_deleted 필드 포함) ===
-- post_id: 1~10에 할당, commenter_id: 1~10 사이에서 랜덤 할당
-- level: 0(최상위), 1(대댓글), 2(대대댓글)
-- is_deleted: 일부 댓글을 true로 설정하여 soft-delete 상태를 시뮬레이션합니다.

-- Post 1 (총 15개 댓글)
-- Level 0 (최상위 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (1, 1, 2, NULL, '정말 좋은 글이네요. 원본 댓글입니다.', '정말 좋은 글이네요. AI가 다듬은 댓글입니다.', 0, false, false,
        NOW(), NOW()),
       (2, 1, 3, NULL, '많이 배우고 갑니다.', '많은 것을 배우고 갑니다.', 0, false, false, NOW(), NOW()),
       (3, 1, 4, NULL, '혹시 이 부분에 대해 더 설명해주실 수 있나요?', '혹시 이 부분에 대해 조금 더 자세히 설명해주실 수 있을까요?', 0, false,
        false, NOW(), NOW()),
       (4, 1, 5, NULL, '[삭제된 댓글] 저는 좀 다르게 생각해요.', '[삭제된 댓글] 저는 조금 다른 관점에서 생각합니다.', 0, true, false,
        NOW(), NOW()),
       (5, 1, 6, NULL, '완전 공감!', '완벽하게 공감합니다!', 0, false, false, NOW(), NOW()),
       (6, 1, 7, NULL, '이런 글을 써주셔서 감사합니다.', '이런 깊이 있는 글을 작성해주셔서 감사합니다.', 0, false, false, NOW(),
        NOW());
-- Level 1 (대댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (7, 1, 8, 1, '맞아요! 저도 그렇게 생각했어요.', '동의합니다! 저도 같은 생각을 했습니다.', 1, false, false, NOW(), NOW()),
       (8, 1, 9, 1, '[삭제된 댓글] 글쓴이의 통찰력이 돋보이네요.', '[삭제된 댓글] 작성자님의 통찰력이 돋보이는 글입니다.', 1, true, false,
        NOW(), NOW()),
       (9, 1, 10, 2, '배우셨다니 다행입니다.', '도움이 되셨다니 다행입니다.', 1, false, false, NOW(), NOW()),
       (10, 1, 1, 3, '어떤 부분이 궁금하신가요?', '어떤 부분이 가장 궁금하신가요?', 1, false, false, NOW(), NOW()),
       (11, 1, 2, 4, '오, 다른 생각도 궁금하네요!', '오, 다른 의견도 들어보고 싶습니다!', 1, false, false, NOW(), NOW()),
       (12, 1, 3, 5, '공감해주셔서 저도 기분이 좋네요.', '공감해주시니 저도 기쁩니다.', 1, false, false, NOW(), NOW());
-- Level 2 (대대댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (13, 1, 4, 7, '두 분 다 대단하세요.', '두 분 모두 정말 대단하십니다.', 2, false, false, NOW(), NOW()),
       (14, 1, 5, 10, '3번째 문단이요!', '세 번째 문단이 특히 궁금합니다!', 2, false, false, NOW(), NOW()),
       (15, 1, 6, 11, '[삭제된 댓글] 제 생각은 이렇습니다...', '[삭제된 댓글] 제 생각은 이러합니다...', 2, true, false, NOW(),
        NOW());

-- Post 2 (총 25개 댓글)
-- Level 0
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (16, 2, 1, NULL, '포스트 2의 첫 댓글', '포스트 2의 첫 번째 댓글입니다.', 0, false, false, NOW(), NOW()),
       (17, 2, 2, NULL, '흥미로운 주제네요.', '매우 흥미로운 주제입니다.', 0, false, false, NOW(), NOW()),
       (18, 2, 3, NULL, '잘 읽었습니다.', '잘 읽고 갑니다.', 0, false, false, NOW(), NOW()),
       (19, 2, 4, NULL, '[삭제된 댓글] 이런 관점도 있군요.', '[삭제된 댓글] 이러한 관점도 존재하군요.', 0, true, false, NOW(),
        NOW()),
       (20, 2, 5, NULL, '다음 글도 기대됩니다.', '다음 글도 기대하겠습니다.', 0, false, false, NOW(), NOW()),
       (21, 2, 6, NULL, '생각할 거리를 주네요.', '생각할 거리를 던져주는 글입니다.', 0, false, false, NOW(), NOW()),
       (22, 2, 7, NULL, '스크랩해 갑니다.', '좋은 글이라 스크랩합니다.', 0, false, false, NOW(), NOW()),
       (23, 2, 8, NULL, '최고예요!', '정말 최고입니다!', 0, false, false, NOW(), NOW()),
       (24, 2, 9, NULL, '시간 가는 줄 모르고 읽었네요.', '시간 가는 줄 모르고 정독했습니다.', 0, false, false, NOW(), NOW()),
       (25, 2, 10, NULL, '정리가 잘 되어있네요.', '내용이 일목요연하게 정리되어 있네요.', 0, false, false, NOW(), NOW());
-- Level 1
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (26, 2, 1, 16, '반갑습니다.', '만나서 반갑습니다.', 1, false, false, NOW(), NOW()),
       (27, 2, 2, 17, '맞아요. 저도 관심 많아요.', '동의합니다. 저도 관심이 많은 분야입니다.', 1, false, false, NOW(), NOW()),
       (28, 2, 3, 17, '관련해서 추천할 만한 책 있나요?', '이와 관련하여 추천할 만한 도서가 있을까요?', 1, false, false, NOW(),
        NOW()),
       (29, 2, 4, 19, '새로운 시각이네요.', '참신한 시각입니다.', 1, false, false, NOW(), NOW()),
       (30, 2, 5, 20, '저도요!', '저도 기대하고 있습니다!', 1, false, false, NOW(), NOW()),
       (31, 2, 6, 21, '[삭제된 댓글] 고민이 깊어지는 밤입니다.', '[삭제된 댓글] 고민이 깊어지는 밤이네요.', 1, true, false, NOW(),
        NOW()),
       (32, 2, 7, 22, '스크랩 감사합니다.', '스크랩해주셔서 감사합니다.', 1, false, false, NOW(), NOW()),
       (33, 2, 8, 23, '최고라니요! 감사합니다.', '최고의 칭찬 감사합니다.', 1, false, false, NOW(), NOW()),
       (34, 2, 9, 24, '재미있게 읽으셨다니 다행입니다.', '재미있게 읽으셨다니 다행이네요.', 1, false, false, NOW(), NOW()),
       (35, 2, 10, 25, '감사합니다. 노력했어요.', '감사합니다. 노력의 산물입니다.', 1, false, false, NOW(), NOW());
-- Level 2
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
VALUES (36, 2, 1, 27, '오, 어떤 점이 관심 가세요?', '오, 어떤 점이 가장 흥미로우신가요?', 2, false, false, NOW(), NOW()),
       (37, 2, 2, 28, '저는 "생각에 관한 생각" 추천해요.', '저는 "생각에 관한 생각"이라는 책을 추천합니다.', 2, false, false, NOW(),
        NOW()),
       (38, 2, 3, 30, '[삭제된 댓글] 작가님 팬이에요!', '[삭제된 댓글] 작가님의 오랜 팬입니다!', 2, true, false, NOW(), NOW()),
       (39, 2, 4, 33, '아니에요. 정말 최고예요.', '아닙니다. 정말 최고의 글입니다.', 2, false, false, NOW(), NOW()),
       (40, 2, 5, 35, '노력이 느껴져요.', '그 노력이 느껴지는 글입니다.', 2, false, false, NOW(), NOW());

-- Post 3 (총 18개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 40 + n,
       3,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P3 댓글 ', n),
       CONCAT('P3 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 47 + n,
       3,
       FLOOR(1 + RAND() * 10),
       40 + n,
       CONCAT('P3 답글 ', n),
       CONCAT('P3 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 54 + n,
       3,
       FLOOR(1 + RAND() * 10),
       47 + n,
       CONCAT('P3 대답글 ', n),
       CONCAT('P3 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) n1) nums;

-- Post 4 (총 22개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 58 + n,
       4,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P4 댓글 ', n),
       CONCAT('P4 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 66 + n,
       4,
       FLOOR(1 + RAND() * 10),
       58 + n,
       CONCAT('P4 답글 ', n),
       CONCAT('P4 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 74 + n,
       4,
       FLOOR(1 + RAND() * 10),
       66 + n,
       CONCAT('P4 대답글 ', n),
       CONCAT('P4 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6) n1) nums;

-- Post 5 (총 10개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 80 + n,
       5,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P5 댓글 ', n),
       CONCAT('P5 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 84 + n,
       5,
       FLOOR(1 + RAND() * 10),
       80 + n,
       CONCAT('P5 답글 ', n),
       CONCAT('P5 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 88 + n,
       5,
       FLOOR(1 + RAND() * 10),
       84 + n,
       CONCAT('P5 대답글 ', n),
       CONCAT('P5 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n FROM (SELECT 1 as n UNION ALL SELECT 2) n1) nums;

-- Post 6 (총 30개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 90 + n,
       6,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P6 댓글 ', n),
       CONCAT('P6 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9
            UNION ALL
            SELECT 10
            UNION ALL
            SELECT 11
            UNION ALL
            SELECT 12) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 102 + n,
       6,
       FLOOR(1 + RAND() * 10),
       90 + n,
       CONCAT('P6 답글 ', n),
       CONCAT('P6 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9
            UNION ALL
            SELECT 10
            UNION ALL
            SELECT 11
            UNION ALL
            SELECT 12) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 114 + n,
       6,
       FLOOR(1 + RAND() * 10),
       102 + n,
       CONCAT('P6 대답글 ', n),
       CONCAT('P6 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6) n1) nums;

-- Post 7 (총 12개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 120 + n,
       7,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P7 댓글 ', n),
       CONCAT('P7 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 125 + n,
       7,
       FLOOR(1 + RAND() * 10),
       120 + n,
       CONCAT('P7 답글 ', n),
       CONCAT('P7 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 130 + n,
       7,
       FLOOR(1 + RAND() * 10),
       125 + n,
       CONCAT('P7 대답글 ', n),
       CONCAT('P7 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n FROM (SELECT 1 as n UNION ALL SELECT 2) n1) nums;

-- Post 8 (총 28개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 132 + n,
       8,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P8 댓글 ', n),
       CONCAT('P8 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9
            UNION ALL
            SELECT 10) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 142 + n,
       8,
       FLOOR(1 + RAND() * 10),
       132 + n,
       CONCAT('P8 답글 ', n),
       CONCAT('P8 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8
            UNION ALL
            SELECT 9
            UNION ALL
            SELECT 10
            UNION ALL
            SELECT 11) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 153 + n,
       8,
       FLOOR(1 + RAND() * 10),
       142 + n,
       CONCAT('P8 대답글 ', n),
       CONCAT('P8 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7) n1) nums;

-- Post 9 (총 20개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 160 + n,
       9,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P9 댓글 ', n),
       CONCAT('P9 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 168 + n,
       9,
       FLOOR(1 + RAND() * 10),
       160 + n,
       CONCAT('P9 답글 ', n),
       CONCAT('P9 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 176 + n,
       9,
       FLOOR(1 + RAND() * 10),
       168 + n,
       CONCAT('P9 대답글 ', n),
       CONCAT('P9 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) n1) nums;

-- Post 10 (총 20개 댓글)
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 180 + n,
       10,
       FLOOR(1 + RAND() * 10),
       NULL,
       CONCAT('P10 댓글 ', n),
       CONCAT('P10 댓글 ', n),
       0,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 188 + n,
       10,
       FLOOR(1 + RAND() * 10),
       180 + n,
       CONCAT('P10 답글 ', n),
       CONCAT('P10 답글 ', n),
       1,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n
            UNION ALL
            SELECT 2
            UNION ALL
            SELECT 3
            UNION ALL
            SELECT 4
            UNION ALL
            SELECT 5
            UNION ALL
            SELECT 6
            UNION ALL
            SELECT 7
            UNION ALL
            SELECT 8) n1) nums;
INSERT INTO comments(id, post_id, commenter_id, parent_id, before_content, after_content, level,
                     is_deleted, is_reported, created_at, updated_at)
SELECT 196 + n,
       10,
       FLOOR(1 + RAND() * 10),
       188 + n,
       CONCAT('P10 대답글 ', n),
       CONCAT('P10 대답글 ', n),
       2,
       IF(RAND() < 0.15, true, false),
       false,
       NOW(),
       NOW()
FROM (SELECT (n1.n) as n
      FROM (SELECT 1 as n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) n1) nums;
