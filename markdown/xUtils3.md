1.集成库

2.初始化
	x.Ext.setDebug(true);
        x.Ext.init(this);

3.请求图片

	图片属性
	imageOptions = new ImageOptions.Builder()
                .setSize(org.xutils.common.util.DensityUtil.dip2px(100), org.xutils.common.util.DensityUtil.dip2px(100))
                .setRadius(org.xutils.common.util.DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.news_pic_default)
                .setFailureDrawableId(R.drawable.news_pic_default)
                .build();
	
	联网请求图片
            x.image().bind(imageView, imageUrl, imageOptions);