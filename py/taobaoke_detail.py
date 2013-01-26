# -*- coding: utf-8 -*-
import top.api
import taobao_env
from db import Mysql
sm = Mysql("sm")


sql = "select name,pic from item where status=1 and name is not null and name !=''";
req=top.api.TaobaokeItemsDetailGetRequest()
#  items, chunk = [1,2,3,4,5,6,7,8,9], 3
#  zip(*[iter(items)]*chunk)
req.fields = "click_url,shop_click_url,seller_credit_score,num_iid,title,nick,pic_url,price"
ret = sm.select(sql)
print len(ret)
size = 10
for num in [ret[i: i + size] for i in range(0, len(ret), size)]:
    req.num_iids = ",".join([x["name"] for x in num])
    try:
        resp= req.getResponse()
        for taobaoke_item_detail in resp.get("taobaoke_items_detail_get_response").get("taobaoke_item_details").get("taobaoke_item_detail"):
            tbPath = taobaoke_item_detail.get("click_url")
            item = taobaoke_item_detail.get("item")
            num_iid = item.get("num_iid")
            price = item.get("price")
            pic_url = item.get("pic_url")
            title = item.get("title")
            sql = "update item set tbPath='%s',newPrice=%s where name=%s" % (tbPath, price, num_iid)
            print sql
            sm.query(sql)
        sm.commit()
        print(resp)
    except Exception,e:
        print(e)
