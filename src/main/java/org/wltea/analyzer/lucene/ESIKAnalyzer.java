package org.wltea.analyzer.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.wltea.analyzer.dic.Dictionary;

public final class ESIKAnalyzer  extends Analyzer{
	private boolean useSmart=true;
	
	public boolean useSmart() {
		return useSmart;
	}

	public void setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	/**
	 * IK分词器Lucene  Analyzer接口实现类
	 * 
	 * 默认细粒度切分算法
	 */
	public ESIKAnalyzer(){
		this(true);
	}
	
	/**
	 * IK分词器Lucene Analyzer接口实现类
	 * 
	 * @param useSmart 当为true时，分词器进行智能切分
	 */
	public ESIKAnalyzer(boolean useSmart){
		super();
		this.useSmart = useSmart;
	}

	/**
	 * 重载Analyzer接口，构造分词组件
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName, final Reader in) {
		Tokenizer _IKTokenizer = new ESIKTokenizer(in , this.useSmart());
		
		return new TokenStreamComponents(_IKTokenizer);
	}
	
    public ESIKAnalyzer(Settings indexSetting,Settings settings1) {
        super();
        Dictionary.initial().Init(indexSetting);
        //settings1.get("use_smart", "false" 含义为取use_smart,若为null,则默认值为fasle
        if(settings1.get("use_smart", "false").equals("true")){
            useSmart = true;
        }else{
        	useSmart = false;
        }
        
    }

}
