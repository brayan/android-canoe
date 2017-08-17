package br.com.sailboat.canoe.view.template;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;

public class TemplateListActivity extends BaseActivitySingleFragment<TemplateListFragment> {

    @Override
    protected TemplateListFragment newFragmentInstance() {
        return new TemplateListFragment();
    }

}
