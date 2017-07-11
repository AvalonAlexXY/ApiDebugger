package io.chengguo.apidebugger.ui.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ex.IdeFocusTraversalPolicy;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.table.JBTable;
import io.chengguo.apidebugger.engine.utils.ViewUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_COLUMN_NAMES;
import static io.chengguo.apidebugger.engine.utils.Constants.DEFAULT_EMPTY_DATA;
import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Created by fingerart on 17/5/17.
 */
public class RequestBodyWidget {
    public JPanel container;
    private JRadioButton mRbFormData;
    private JRadioButton mRbXWwwFormUrlencoded;
    private JRadioButton mRbRaw;
    private JRadioButton mRbBinary;
    private JPanel headerContainer;
    private JBTable mFormData;
    private JBTable mUrlencoded;
    private EditorTextField mEtfRaw;
    private ButtonGroup TypeBody;

    private CardLayout headerTypeCardLayout;
    private Project project;

    public RequestBodyWidget(Project project) {
        this.project = project;
        headerTypeCardLayout = (CardLayout) headerContainer.getLayout();

        mFormData.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mFormData.getTableHeader().setReorderingAllowed(false);

        mUrlencoded.setModel(new DefaultTableModel(DEFAULT_EMPTY_DATA, DEFAULT_COLUMN_NAMES));
        mUrlencoded.getTableHeader().setReorderingAllowed(false);

        mEtfRaw.setBorder(null);
        mEtfRaw.setFocusTraversalPolicy(new IdeFocusTraversalPolicy());

        setCursor(Cursor.HAND_CURSOR, mRbFormData, mRbXWwwFormUrlencoded, mRbRaw, mRbBinary);

        mRbFormData.addActionListener(headerTypeListener);
        mRbXWwwFormUrlencoded.addActionListener(headerTypeListener);
        mRbRaw.addActionListener(headerTypeListener);
        mRbBinary.addActionListener(headerTypeListener);


    }

    private final ActionListener headerTypeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            headerTypeCardLayout.show(headerContainer, e.getActionCommand());
        }
    };

    public String bodyType() {
        return TypeBody.getSelection().getActionCommand();
    }

    public Map<String, String> bodyUrlencode() {
        return ViewUtil.getTableContent(mUrlencoded.getModel());
    }
}
