-- ベースとなるテーブル群を作ってしまう。
-- (基本的に、最初のバージョンはDDLを入れとくの良いかな？)
CREATE TABLE member (
    member_id     INT UNSIGNED   PRIMARY KEY NOT NULL,
    name          TEXT                       NOT NULL,
    kana          TEXT                       NOT NULL
);