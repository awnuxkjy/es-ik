package org.wltea.analyzer.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.wltea.analyzer.dic.Dictionary;

public final class ESIKAnalyzer  extends Analyzer{
	private boolean useSmart;
	
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
		this(false);
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
	
    public ESIKAnalyzer(Settings indexSetting,Settings settings) {
        super();
        Dictionary.initial().Init(indexSetting);
        //settings1.get("use_smart", "true")含义为取use_smart,若为null,则默认值为true
        if(settings.get("use_smart", "true").equals("true")){
            useSmart = true;
        }
        
    }

}
