package io.github.sing1ee.autocomplete;

import java.util.List;

/**
 * Created by zhangcheng on 15/3/4.
 */
public interface AutoComplete {

    public void add(String text);

    public void build(List<String> texts);

    public boolean contains(String text);

    public Iterable<String> keys();

    public Iterable<String> keysWithPrefix(String prefix);
}
