insert into member(member_id, name, kana) values ((SELECT MAX(member_id) + 1 FROM member), 'kazuhito_m', 'ナンデキタン');
