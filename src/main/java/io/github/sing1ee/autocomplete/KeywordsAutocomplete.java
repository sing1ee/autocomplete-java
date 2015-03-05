package io.github.sing1ee.autocomplete;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import io.github.sing1ee.autocomplete.tst.TernarySearchTree;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangcheng on 15/3/5.
 */
public class KeywordsAutocomplete {


    private String lang = "en";
    private TernarySearchTree tst;
    private Map<String, String> suffixMap;

    public KeywordsAutocomplete(String lang) {
        if (null != lang)
            this.lang = lang;
        tst = new TernarySearchTree();
        suffixMap = Maps.newHashMap();
    }

    private void suffixAdd(String text, TernarySearchTree tst) {
        List<String> searchTypes = Lists.newArrayListWithCapacity(3);
        searchTypes.add(text);
        if (lang.equalsIgnoreCase("ch")) {
            searchTypes.add(PinyinHelper.getShortPinyin(text));
            searchTypes.add(PinyinHelper.convertToPinyinString(text, "", PinyinFormat.WITHOUT_TONE));
        }
        for (String str : searchTypes) {
            if (str.length() > 3) {
                for (int i = 0; i < str.length() - 3; ++i) {
                    String key = str.substring(i);
                    tst.add(key);
                    suffixMap.put(key, searchTypes.get(0));
                }
            } else {
                tst.add(str);
                suffixMap.put(str, searchTypes.get(0));
            }
        }
    }

    public void add(String text) {
        suffixAdd(text, tst);
    }

    public void load(String keywordsFile) throws Exception {
        Files.readLines(new File(keywordsFile), Charsets.UTF_8).stream().forEach(t -> {
            suffixAdd(t, tst);
        });
    }

    public Set<String> search(String prefix) {

        return Sets.newHashSet(Lists.transform(Lists.newLinkedList(tst.keysWithPrefix(prefix)), new Function<String, String>() {
            @Override
            public String apply(String name) {
                if (suffixMap.containsKey(name)) {
                    return suffixMap.get(name);
                }
                return name;
            }
        }));
    }
}
