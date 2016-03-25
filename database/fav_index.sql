create index idx_applier_id on tb_apply_record(applier_id);

create index idx_auction_bid_fav_id on tb_auction_collection_bid(collection_id);

create index idx_collection_images_fav_id on tb_collection_images(collection_id);

create index idx_dynamic_images_dynamic_id on tb_dynamic_images(dynamic_id);

create index idx_dynamic_like_dynamic_id on tb_dynamic_like(dynamic_id);

create index idx_favorites_user_id on tb_favorites(user_id);

create index idx_messages_sender on tb_messages(sender);

create index idx_messages_receiver on tb_messages(receiver);

create index idx_sms_phone on tb_sms(phone);

create index idx_fav_user_phone on tb_fav_user(phone);

create index idx_user_fans_user_id on tb_user_fans(user_id);

create index idx_user_points_record_userid on tb_user_points_record(userid);

create index idx_comment_source_id on tb_comment(source_id);

create index idx_comment_friend_id on tb_comment(friend_id);

create index idx_comment_like_source_id on tb_comment_like(source_id);

create index idx_comment_top_source_id on tb_comment_top(source_id);

create index idx_fav_user_set_user_id on tb_fav_user_set(user_id);

create index idx_user_blacklist_user_id on tb_user_blacklist(user_id);

create index idx_user_interes_category_user_id on tb_user_interes_category(user_id);

create index idx_collection_insert_date_desc on tb_collection(insert_date desc);

create index idx_collection_insert_id on tb_collection(insert_id);

create index idx_collection_category_id on tb_collection(category_id);

create index idx_collection_collection_period_id on tb_collection(collection_period_id);

create index idx_collection_label_id on tb_collection(label_id);

create index idx_collection_status on tb_collection(status);

create index idx_comment_top_comment_id on tb_comment_top(comment_id);

create index idx_comment_like_comment_id on tb_comment_like(comment_id);

create index idx_comment_reply_id on tb_comment(reply_id);

create index idx_messages_is_read on tb_messages(is_read);

create index idx_daily_polemic_vote_d_p_id on tb_daily_polemic_vote(daily_polemic_id);

create index idx_daily_polemic_vote_user_id on tb_daily_polemic_vote(user_id);

create index idx_dynamic_release_by on tb_dynamic(release_by);

create index idx_dynamic_insert_id on tb_dynamic(insert_id);

ALTER TABLE tb_apply_record drop index idx_applier_id;

ALTER TABLE tb_auction_collection_bid drop index idx_auction_bid_fav_id;

ALTER TABLE tb_collection_images drop index idx_collection_images_fav_id;

ALTER TABLE tb_dynamic_images drop index idx_dynamic_images_dynamic_id;

ALTER TABLE tb_dynamic_like drop index idx_dynamic_like_dynamic_id;

ALTER TABLE tb_favorites drop index idx_favorites_user_id;

ALTER TABLE tb_messages drop index idx_messages_sender;

ALTER TABLE tb_messages drop index idx_messages_receiver;

ALTER TABLE tb_sms drop index idx_sms_phone;

ALTER TABLE tb_fav_user drop index idx_fav_user_phone;

ALTER TABLE tb_user_fans drop index idx_user_fans_user_id;

ALTER TABLE tb_user_points_record drop index idx_user_points_record_userid;

ALTER TABLE tb_comment drop index idx_comment_source_id;

ALTER TABLE tb_comment drop index idx_comment_friend_id;

ALTER TABLE tb_comment_like drop index idx_comment_like_source_id;

ALTER TABLE tb_comment_top drop index idx_comment_top_source_id;

ALTER TABLE tb_fav_user_set drop index idx_fav_user_set_user_id;

ALTER TABLE tb_user_blacklist drop index idx_user_blacklist_user_id;

ALTER TABLE tb_user_interes_category drop index idx_user_interes_category_user_id;