// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.json.ui.wizards;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.talend.core.model.context.JobContextManager;
import org.talend.core.model.metadata.builder.connection.MetadataTable;
import org.talend.core.model.properties.ContextItem;
import org.talend.repository.json.util.JSONConnectionContextHelper;
import org.talend.repository.model.IConnParamName;
import org.talend.repository.ui.swt.utils.AbstractForm;
import org.talend.repository.ui.wizards.metadata.connection.files.xml.treeNode.FOXTreeNode;

/**
 * hwang class global comment. Detailled comment
 */
public abstract class AbstractJSONStepForm extends AbstractForm {

    private WizardPage page;

    /**
     * wchen AbstractXmlStepForm constructor comment.
     * 
     * @param parent
     * @param style
     */
    public AbstractJSONStepForm(Composite parent, int style, String[] existingNames) {
        super(parent, style, existingNames);
    }

    public TreeViewer getTreeViewer() {
        return null;
    }

    public abstract void redrawLinkers();

    public abstract void updateConnection();

    public abstract void updateStatus();

    public abstract List<FOXTreeNode> getTreeData();

    public abstract void setSelectedText(String label);

    public abstract MetadataTable getMetadataTable();

    public abstract MetadataTable getMetadataOutputTable();

    public abstract TableViewer getSchemaViewer();

    public WizardPage getPage() {
        return this.page;
    }

    public void setPage(WizardPage page) {
        this.page = page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#exportAsContext()
     */
    @Override
    protected void exportAsContext() {
        if (hasContextBtn() && connectionItem != null) {
            if (isContextMode()) {
                JSONConnectionContextHelper.openInConetxtModeDialog();
            } else {
                ContextItem contextItem = JSONConnectionContextHelper.exportAsContext(connectionItem, getConetxtParams());
                contextManager = JSONConnectionContextHelper.contextManager;
                if (contextItem != null) { // create
                    if (contextManager instanceof JobContextManager) {
                        Map<String, String> map = ((JobContextManager) contextManager).getNameMap();
                        // set properties for context mode
                        JSONConnectionContextHelper.setPropertiesForContextMode(connectionItem, contextItem, getConetxtParams(),
                                map);
                    }
                    // refresh current UI.
                    initialize();
                    adaptFormToEditable();
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#revertContext()
     */
    @Override
    protected void revertContext() {
        // TODO Auto-generated method stub
        super.revertContext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.repository.ui.swt.utils.AbstractForm#getConetxtParams()
     */
    @Override
    protected Set<IConnParamName> getConetxtParams() {
        // TODO Auto-generated method stub
        return super.getConetxtParams();
    }

}
