---
---图片enable 默认值
---
ALTER TABLE `astalift`.`title_image`
CHANGE COLUMN `enable` `enable` BIT(1) NOT NULL DEFAULT 1 ;
