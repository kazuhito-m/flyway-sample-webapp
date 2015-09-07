insert into member(member_id, name, kana) SELECT MAX(member_id) + 1 , 'kazuhito_m', 'ナンデキタン' FROM member;
