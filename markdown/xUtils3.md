1.���ɿ�

2.��ʼ��
	x.Ext.setDebug(true);
        x.Ext.init(this);

3.����ͼƬ

	ͼƬ����
	imageOptions = new ImageOptions.Builder()
                .setSize(org.xutils.common.util.DensityUtil.dip2px(100), org.xutils.common.util.DensityUtil.dip2px(100))
                .setRadius(org.xutils.common.util.DensityUtil.dip2px(5))
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.news_pic_default)
                .setFailureDrawableId(R.drawable.news_pic_default)
                .build();
	
	��������ͼƬ
            x.image().bind(imageView, imageUrl, imageOptions);