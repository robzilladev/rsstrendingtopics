package rsstrendingtopcis;

import java.awt.Color;import java.awt.Cursor;
import java.awt.event.ActionEvent;import java.awt.event.ActionListener;
import java.io.File;import java.io.FileNotFoundException;
import java.io.IOException;import java.net.MalformedURLException;
import java.security.InvalidKeyException;import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;import java.text.ParseException;
import java.util.ArrayList;import java.util.List;
import java.util.logging.Level;import java.util.logging.Logger;
import javax.crypto.BadPaddingException;import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultListModel;import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;import org.jdom2.Document;
import org.jdom2.JDOMException;
// Using SKELETON CODE created by Trent Lewis - Flinders University.

/**
 * Builds GUI and handles all event based processing. Contains listener subclasses
 * to handle all event based processing. Populates JLIST based on sorting and trending
 * criteria.
 * @author Rob Bloomfield
 * @see ListHandler List Handler
 * @see TrendingIndex Trending Index
 */
public class RSSTrendingTopicsFrame extends javax.swing.JFrame {;
    
    // All models needed for GUI lists.
    /**
     * Model only used when 'default' is selected as sorting criteria, or for
     * initial loading of feeds. This model should never be manipulated.
     */
    private DefaultListModel<RSSFeedItem> model;
    /**
     * Model to be used when any sorting criteria is selected OTHER than default.
     * Any data manipulation should happen to this sorted model.
     */
    private DefaultListModel<RSSFeedItem> sortModel;
    /**
     * Model to be used when a trending word is selected from the trending jlist.
     */
    private DefaultListModel<RSSFeedItem> tModel;
    /**
     * Model for the displaying of all trending words in the trending jlist.
     */
    private DefaultListModel<Word> trendModel;
    
    /**
     * Returns true if a listener is currently active on the primary jlist.
     */
    private boolean listener;
    /**
     * Returns true if the list being used is currently sorted.
     */
    private boolean listSorted;
    /**
     * Returns true if the RSSFeedItems have been analysed for trending words.
     */
    private boolean indexed = false;
    
    /**
     * Listener for the primary jlist.
     */
    private final ListListen ls;
    /**
     * Listener for the trending jlist.
     */
    private final TrendListen tl;
    /**
     * Listener for all sorting radio buttons.
     */
    private final RadioListen rl;
    /**
     * Listener for all sorting combo box items.
     */
    private final ComboListen cl;
    /**
     * Listener for all button components.
     */
    private final ButtonListen bl;
    /**
     * Listener for the load text field.
     */
    private final EnterListen el;
    
    /**
     * Shows the current status of the application. Shows feed total, current
     * sorting criteria, number of trending words etc.
     */
    private String status;
    /**
     * Number of RSSFeedItems loaded.
     */
    private int items;
    /**
     * Number of total feeds loaded.
     */
    private int totalFeeds;
    /**
     * Current sorting criteria.
     */
    private String sortedBy;
    /**
     * Colour for sorting identification (sorted, default and/or reverse)
     */
    private final Color sortedColor, defaultColor, reverseColor;
    
    // Indexer - For the indexing of commonly occuring words
    // ListHandler - For all list handling
    TrendingIndex indexer = new TrendingIndex();
    ListHandler listHandler = new ListHandler();
    
    /**
     * Creates new form RSSTrendingTopicsFrame
     */
    public RSSTrendingTopicsFrame() {
        initComponents();
        this.setTitle("RSS.Trender");
        this.el = new EnterListen();
        this.bl = new ButtonListen();
        this.cl = new ComboListen();
        this.rl = new RadioListen();
        this.tl = new TrendListen();
        this.ls = new ListListen();
        
        this.listSorted = false;
        this.listener = false;
        // Adds listeners to required elements.
        radioDefault.addActionListener(rl);
        radioFeedTitle.addActionListener(rl);
        radioItemTitle.addActionListener(rl);
        radioDescription.addActionListener(rl);
        radioDate.addActionListener(rl);
        radioFeedDescription.addActionListener(rl);
        radioMore.addActionListener(rl);
        jButtonTrending.addActionListener(bl);
        jButtonHelp.addActionListener(bl);
        aboutClose.addActionListener(bl);
        jTextFieldLoadFileName.addActionListener(el);
        
        // centers frames on screen.
        this.setLocationRelativeTo(null);
        
        // Sets colours.
        sortedColor = new Color(220,252,248);
        defaultColor = new Color(240,240,240);
        reverseColor = new Color(255,222,241);
        
        // Adds items to combo box
        moreBox.addItem("Descending Feed Title");
        moreBox.addItem("Descending Item Title");
        moreBox.addItem("Descending Item Description");
        moreBox.addItem("Descending Feed Description");
        moreBox.addItem("Descending Date");
        moreBox.addItem("Descending Author");
        moreBox.addItem("Author");
        moreBox.addItem("Item URL");
        moreBox.addItem("Trending Words #");
        moreBox.addItem("Randomise");
        
        trendModel = new DefaultListModel();
        tModel = new DefaultListModel();
        
        status = "Welcome to Rss.Trender, enter the name of the file that contains your RSS feeds and click load...";
        jTextFieldStatus.setText(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        buttonGroupSort = new javax.swing.ButtonGroup();
        aboutFrame = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        aboutClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLoadFileName = new javax.swing.JTextField();
        jButtonLoad = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFeedItems = new javax.swing.JList();
        jPanelFeedItemDetails = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFeedTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldItemTitle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldItemLink = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldItemDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaItemDescription = new javax.swing.JTextArea();
        jTextFieldFeedDescription = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldAuthor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radioDefault = new javax.swing.JRadioButton();
        radioFeedTitle = new javax.swing.JRadioButton();
        radioItemTitle = new javax.swing.JRadioButton();
        radioDate = new javax.swing.JRadioButton();
        radioDescription = new javax.swing.JRadioButton();
        moreBox = new javax.swing.JComboBox();
        radioFeedDescription = new javax.swing.JRadioButton();
        radioMore = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListTrending = new javax.swing.JList();
        jButtonTrending = new javax.swing.JButton();
        jTextFieldStatus = new javax.swing.JTextField();
        jButtonHelp = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("About"));

        jLabel9.setText("RSS.Trender is a simple RSS Feed analysing application.");

        jLabel10.setText("It reads in a list of RSS Feeds, either from a file or a URL,");

        jLabel11.setText("and provides various sorting functions along with logically");

        jLabel12.setText("separated information about the particular RSS item.");

        jLabel13.setText("Application by Robert Bloomfield (Flinders University).");

        jLabel14.setText("Skeleton code supplied by Trent Lewis (Flinders University).");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sorting"));

        jLabel15.setText("[      ]");

        jLabel16.setText("[      ]");

        jLabel17.setText("Highlights the normal sorting criteria field.");

        jLabel18.setText("Highlights the reversed sorting criteria field.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        aboutClose.setText("Done");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aboutClose)
                .addGap(139, 139, 139))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutClose))
        );

        javax.swing.GroupLayout aboutFrameLayout = new javax.swing.GroupLayout(aboutFrame.getContentPane());
        aboutFrame.getContentPane().setLayout(aboutFrameLayout);
        aboutFrameLayout.setHorizontalGroup(
            aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        aboutFrameLayout.setVerticalGroup(
            aboutFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(606, 659));
        setResizable(false);

        jLabel1.setText("Source");

        jTextFieldLoadFileName.setText("tech_rssfeed_filelist.txt");

        jButtonLoad.setMnemonic('l');
        jButtonLoad.setText("Load");
        jButtonLoad.setToolTipText("Load the RSS feed items from the \"source\".");
        jButtonLoad.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonLoadActionPerformed(evt);
            }
        });

        jListFeedItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListFeedItems);

        jPanelFeedItemDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Feed Item Details"));

        jLabel2.setText("Feed Title");

        jTextFieldFeedTitle.setEditable(false);

        jLabel3.setText("Item Title");

        jTextFieldItemTitle.setEditable(false);

        jLabel4.setText("Item Link");

        jTextFieldItemLink.setEditable(false);

        jLabel5.setText("Item Date");

        jTextFieldItemDate.setEditable(false);

        jLabel6.setText("Item Description");

        jTextAreaItemDescription.setEditable(false);
        jTextAreaItemDescription.setColumns(20);
        jTextAreaItemDescription.setLineWrap(true);
        jTextAreaItemDescription.setRows(5);
        jTextAreaItemDescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaItemDescription);

        jTextFieldFeedDescription.setEditable(false);

        jLabel7.setText("Feed Description");

        jTextFieldAuthor.setEditable(false);

        jLabel8.setText("Author");

        javax.swing.GroupLayout jPanelFeedItemDetailsLayout = new javax.swing.GroupLayout(jPanelFeedItemDetails);
        jPanelFeedItemDetails.setLayout(jPanelFeedItemDetailsLayout);
        jPanelFeedItemDetailsLayout.setHorizontalGroup(
            jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFeedItemDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jTextFieldItemTitle, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldItemLink, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFeedItemDetailsLayout.createSequentialGroup()
                        .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldFeedTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFeedItemDetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFeedItemDetailsLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextFieldFeedDescription)))
                            .addGroup(jPanelFeedItemDetailsLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelFeedItemDetailsLayout.createSequentialGroup()
                        .addComponent(jTextFieldItemDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelFeedItemDetailsLayout.setVerticalGroup(
            jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFeedItemDetailsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFeedTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFeedDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldItemTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldItemLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFeedItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldItemDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sort By"));

        buttonGroupSort.add(radioDefault);
        radioDefault.setText("Default");
        radioDefault.setEnabled(false);

        buttonGroupSort.add(radioFeedTitle);
        radioFeedTitle.setText("Feed Title");
        radioFeedTitle.setToolTipText("Sort items by the FEED TITLE.");
        radioFeedTitle.setEnabled(false);

        buttonGroupSort.add(radioItemTitle);
        radioItemTitle.setText("Item Title");
        radioItemTitle.setToolTipText("Sort items by the ITEM TITLE.");
        radioItemTitle.setEnabled(false);

        buttonGroupSort.add(radioDate);
        radioDate.setText("Date");
        radioDate.setToolTipText("Sort items by the DATE PUBLISHED.");
        radioDate.setEnabled(false);

        buttonGroupSort.add(radioDescription);
        radioDescription.setText("Item Descr.");
        radioDescription.setToolTipText("Sort items by the LENGTH OF DESCRIPTION.");
        radioDescription.setEnabled(false);

        moreBox.setEnabled(false);

        buttonGroupSort.add(radioFeedDescription);
        radioFeedDescription.setText("Feed Descr.");
        radioFeedDescription.setToolTipText("Sort items by the LENGTH OF THE FEED DESCRIPTION");
        radioFeedDescription.setEnabled(false);

        buttonGroupSort.add(radioMore);
        radioMore.setText("..");
        radioMore.setToolTipText("More sorting options..");
        radioMore.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioDefault)
                .addGap(14, 14, 14)
                .addComponent(radioFeedTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioItemTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioFeedDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioMore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(moreBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioDefault)
                    .addComponent(radioFeedTitle)
                    .addComponent(radioItemTitle)
                    .addComponent(radioDate)
                    .addComponent(radioDescription)
                    .addComponent(moreBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioFeedDescription)
                    .addComponent(radioMore)))
        );

        jListTrending.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListTrending);

        jButtonTrending.setMnemonic('t');
        jButtonTrending.setText("Trending");
        jButtonTrending.setToolTipText("Search for trending topics.");
        jButtonTrending.setEnabled(false);

        jTextFieldStatus.setEditable(false);
        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldStatusActionPerformed(evt);
            }
        });

        jButtonHelp.setMnemonic('h');
        jButtonHelp.setText("?");
        jButtonHelp.setToolTipText("Open help / about.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFeedItemDetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLoadFileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLoad))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButtonTrending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldLoadFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTrending, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFeedItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldStatus)
                    .addComponent(jButtonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Loads feeds from the specified file, extracts RSSFeedItems, and displays
     * in the Jframe.
     * @param evt Event fired.
     */
    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        
        List<Document> feeds = null;
        listSorted = false;
        indexed = false;
        
        // Clears any trending lists that may be populated.
        jListTrending.removeListSelectionListener(tl);
        if (trendModel != null)
        {
            if(!trendModel.isEmpty())
                clearTrending();
        }
        if (listHandler.getTrending() != null)
        {
            if (listHandler.trendingPopulated())
                listHandler.clearTrending();
        }
        // Clears the sorted list which will be repopulated once the default list
        // has been extracted.
        if (listHandler.getSorted() != null)
        {
            if (listHandler.sortPopulated())
                listHandler.clearSorted();
        }
        
        // Removes list listener if one exists while JLIST is manipulated, to
        // avoid nullPointer conflicts.
        if (listener == true)
            jListFeedItems.removeListSelectionListener(ls);
       
        try {
            // Get the fileName to load from the GUI
            String fileName = this.jTextFieldLoadFileName.getText();

            // Load the file and all the feed data
            // Assumes the file is located in the "feeds" directory.
            feeds = RSSFeedRetriever.getFeeds("feeds" + File.separator + fileName);
            totalFeeds = feeds.size();

        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, "Malformed URL.");
            Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(Level.SEVERE, null, ex);
            jTextFieldLoadFileName.requestFocus();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Could not find specified file.");
            Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(Level.SEVERE, null, ex);
            jTextFieldLoadFileName.requestFocus();
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException | JDOMException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Something went wrong.");
            Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RSSParseException ex)
        {
            Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (feeds != null) {
            // Passes the list of feeds to the extractor and returns a list
            // of individual RSS Feed items.
            List<Document> docList = new ArrayList<>(); // List of RSS documents
            RSSFeedItemExtractor extractor = new RSSFeedItemExtractor();
            List<RSSFeedItem> list;
            // Adds all the documents in FEEDS to docList.
            for (int i=0; i<feeds.size();i++)
            {
                  docList.add(feeds.get(i));
            }
                try
                {
                // Extracts all items from the documents within docList, which is
                // passed to the method call, and passes them to the list handler.
                    list = extractor.extractItems(docList);
                    listHandler.initialiseDefaultAndSorted(list);
                } 
                catch (ParseException ex)
                {
                    Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
           
                // model - for whenever 'default' is the selected sort criteria.
                //       - no processing occurs on model.
                // sortModel - for whenever any sort has occured.
                model = new DefaultListModel<>();
                sortModel = new DefaultListModel<>();
                
                // Adds elements of 'list' from the handler to the model.
                for (int i =0;i<listHandler.getList().size();i++)
                {
                    model.addElement(listHandler.getList().get(i));
                }
                
                // Sets the JList model to the default model and adds listener.
                this.jListFeedItems.setModel(model);
                this.jListFeedItems.addListSelectionListener(ls);
                listener = true;
                
                // Sets the selected index to the first element and displays it.
                // Sets sorting and status variables.
                // Enables all relevant components.
                setIndex();
                sortedBy = "Default";
                enableButtons();
                setStatus();
                moreBox.setEnabled(false);
                moreBox.addActionListener(cl);  
                returnColoursToDefault();
                
                // Notifies the user of the number of feeds that were loaded.
                // If any errors occured, they are also reported.
            if (RSSFeedRetriever.errorCount > 0)
                JOptionPane.showMessageDialog(this, "Loaded RSS data from " 
                        + feeds.size() +" feeds.\nErrors found in " 
                        + RSSFeedRetriever.errorCount + " feeds.\nThese feeds "
                        + "were skipped.");
            else
                JOptionPane.showMessageDialog(this, "Loaded RSS data from " 
                        + feeds.size() +" feeds.");  
                        totalFeeds = feeds.size();
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldStatusActionPerformed
    {//GEN-HEADEREND:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            // Set System L&F
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RSSTrendingTopicsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RSSTrendingTopicsFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutClose;
    private javax.swing.JFrame aboutFrame;
    private javax.swing.ButtonGroup buttonGroupSort;
    private javax.swing.JButton jButtonHelp;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonTrending;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListFeedItems;
    private javax.swing.JList jListTrending;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelFeedItemDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaItemDescription;
    private javax.swing.JTextField jTextFieldAuthor;
    private javax.swing.JTextField jTextFieldFeedDescription;
    private javax.swing.JTextField jTextFieldFeedTitle;
    private javax.swing.JTextField jTextFieldItemDate;
    private javax.swing.JTextField jTextFieldItemLink;
    private javax.swing.JTextField jTextFieldItemTitle;
    private javax.swing.JTextField jTextFieldLoadFileName;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JComboBox moreBox;
    private javax.swing.JRadioButton radioDate;
    private javax.swing.JRadioButton radioDefault;
    private javax.swing.JRadioButton radioDescription;
    private javax.swing.JRadioButton radioFeedDescription;
    private javax.swing.JRadioButton radioFeedTitle;
    private javax.swing.JRadioButton radioItemTitle;
    private javax.swing.JRadioButton radioMore;
    // End of variables declaration//GEN-END:variables
  
//------------------------------------------------------------------------------
// Setters for all changing SWING values.
//------------------------------------------------------------------------------
    /**
     * Sets feed title component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
        public void setFeedTitle(String s)
    {
        jTextFieldFeedTitle.setText(s);
    }

    /**
     * Sets feed description component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
    public void setFeedDescription(String s)
    {
        jTextFieldFeedDescription.setText(s);
    }

    /**
     * Sets item title component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
    public void setItemTitle(String s)
    {
        jTextFieldItemTitle.setText(s);
    }

    /**
     * Sets item link component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
    public void setItemLink(String s)
    {
        jTextFieldItemLink.setText(s);
    }

    /**
     * Sets item date component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
    public void setItemDate(String s)
    {
        jTextFieldItemDate.setText(s);
    }

    /**
     * Sets item author component to appropriate RSSFeedItem data.
     * @param s String to be set
     */
    public void setAuthor(String s)
    {
        jTextFieldAuthor.setText(s);
    }

    /**
     * Sets item description component to appropriate RSSFeedItem data.
     * @param s String to be set.
     */
    public void setItemDescription(String s)
    {
        jTextAreaItemDescription.setText(s);
    }

    /**
     * Returns all carets to index 0. Ensures that strings which are longer than
     * the GUI field allocated are returned to the start of their string on display.
     */
    public void setCarets()
    {
        jTextFieldItemLink.setCaretPosition(0);
        jTextFieldFeedDescription.setCaretPosition(0);
        jTextAreaItemDescription.setCaretPosition(0);
        jListFeedItems.requestFocus();
    }

    /**
     * Sets index of the primary jlist to 0. Ensures that the first element in the
     * list is always visible and selected after sorting and trending.
     */
    public void setIndex()
    {
        jListFeedItems.setSelectedIndex(0);
        jListFeedItems.ensureIndexIsVisible(0);
    }

    /**
     * Sets status information.
     */
    public void setStatus()
    {
        items = listHandler.getList().size();
        status = "RSS Items: " + items + "\t| Total Feeds: "+ totalFeeds 
                +"\t| Sorted by: " + sortedBy + 
                "\t| Trending: " + trendModel.size() +"/" + indexer.getWordCount() ;
        jTextFieldStatus.setText(status);
    }
    
    /**
     * Enables all buttons.
     */
    public void enableButtons()
    {
        radioDefault.setEnabled(true);
        radioDefault.setSelected(true);
        radioFeedTitle.setEnabled(true);
        radioItemTitle.setEnabled(true);
        radioDate.setEnabled(true);
        radioFeedDescription.setEnabled(true);
        radioMore.setEnabled(true);
        radioDescription.setEnabled(true);
        jButtonTrending.setEnabled(true);
    }

    /**
     * Returns all GUI colours back to default.
     */
    public void returnColoursToDefault()
    {
        jTextFieldFeedTitle.setBackground(defaultColor);
        jTextFieldItemTitle.setBackground(defaultColor);
        jTextFieldItemLink.setBackground(defaultColor);
        jTextFieldItemDate.setBackground(defaultColor);
        jTextAreaItemDescription.setBackground(Color.white);
        jTextFieldFeedDescription.setBackground(defaultColor);
        jTextFieldAuthor.setBackground(defaultColor);
    }

    /**
     * Clears trending list and model.
     */
    public void clearTrending()
    {
        indexer.clearTrend();
        trendModel.clear();
    }

    /**
     * Gets feed title from appropriate item, based on what list is currently being
     * used.
     * @return Feed title.
     */
    public String getFeedTitle()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getFeedTitle();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getFeedTitle();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getFeedTitle();
        }
        return s;
    }

    /**
     * Gets feed description from appropriate item, based on what list is currently being
     * used.
     * @return Feed description.
     */
    public String getFeedDescription()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getFeedDescription();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getFeedDescription();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getFeedDescription();
        }
        return s;
    }

    /**
     * Gets item title from appropriate item, based on what list is currently being
     * used.
     * @return Item title.
     */
    public String getItemTitle()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getItemTitle();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getItemTitle();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getItemTitle();
        }
        return s;
    }

    /**
     * Gets item link from appropriate item, based on what list is currently being
     * used.
     * @return Item link/URL.
     */
    public String getItemLink()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getItemLink();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getItemLink();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getItemLink();
        }
        return s;
    }

    /**
     * Gets item date from appropriate item, based on what list is currently being
     * used.
     * @return Item date.
     */
    public String getItemDate()
    {
        String s;
        if (listSorted == false)
        {
            s = model.get(jListFeedItems.getSelectedIndex()).getItemDate().toString();
        }
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getItemDate().toString();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getItemDate().toString();
        }
        return s;
    }

    /**
     * Gets item description from appropriate item, based on what list is currently being
     * used.
     * @return Item description.
     */
    public String getItemDescription()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getItemDescription();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getItemDescription();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getItemDescription();
        }
        return s;
    }

    /**
     * Gets item author from appropriate item, based on what list is currently being
     * used.
     * @return Item author.
     */
    public String getAuthor()
    {
        String s;
        if (listSorted == false)
            s = model.get(jListFeedItems.getSelectedIndex()).getAuthor();
        else
        {
            if (indexed == true)
                s = tModel.get(jListFeedItems.getSelectedIndex()).getAuthor();
            else
                s = sortModel.get(jListFeedItems.getSelectedIndex()).getAuthor();
        }
        return s;
    }

    /**
     * Gets the selected trending word.
     * @return Selected trending word.
     */
    public String getTrendingWord()
    {
        return trendModel.get(jListTrending.getSelectedIndex()).getWord();
    }
    
    /**
     * Sets sortModel to active, adds the listener and sets related booleans to true.
     */
    public void setSortModelHelper()
    {
        jListFeedItems.setModel(sortModel);
        listSorted = true;
        jListFeedItems.addListSelectionListener(ls);
        listener = true;
    }
    
    /**
     * Adds all elements in the passed list to the required model.
     * @param model Model to be added to (sorted/trending).
     * @param l List to add from.
     */
    public void addToModel(String model, List<RSSFeedItem> l)
        {
        switch (model)
        {
            case "sorted":
                sortModel.clear();
                for (int i = 0; i<l.size(); i++)
                {
                    sortModel.addElement(l.get(i));
                }
                break;
            case "trending":
                tModel.clear();
                for (int i = 0; i<l.size(); i++)
                {
                    tModel.addElement(l.get(i));
                }
                break;
        }
        }

    /**
     * Returns the GUI back to its initial loaded state. Sets default model as the
     * active model and resets all listeners and associated booleans and colours.
     */
    public void setDefault()
    {
        jListFeedItems.setModel(model);
        jListFeedItems.addListSelectionListener(ls);
        listener = true;
        sortedBy = "Default";
        indexed = false;
        returnColoursToDefault();
        setIndex();
        setStatus();
    }

    /**
     * Gets the sizes of both the default and sorted lists. Included for testing purposes.
     */
    public void getSizes()
    {
        System.out.println("Default: "+listHandler.getList().size());
        System.out.println("Sorted: "+listHandler.getSorted().size());
    }
    
    /**
     * Adds all RSSFeedITem objects that are associated with the passed word
     * to the trending list. Sets the model to trending and populates accordingly.
     * @param word Word to search for.
     */
    public void displayTrendingItems(String word)
    {
        jListFeedItems.removeListSelectionListener(ls);
        if (listHandler.trendingPopulated())
            listHandler.clearTrending();
        for (RSSFeedItem i: listHandler.getSorted())
        {
            if (i.getWords().contains(word))
            {
                listHandler.addToTrending(i);
            }
        }
        addToModel("trending",listHandler.getTrending());
        jListFeedItems.setModel(tModel);
        jListFeedItems.addListSelectionListener(ls);
    }
    
    /**
     * Listener for the primary JLIST. Sets all GUI components to their appropriate
     * text.
     */
    class ListListen implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent lse)
        {
            // Update Feed Title
            setFeedTitle(getFeedTitle());
            // Update Feed Description
            setFeedDescription(getFeedDescription());
            // Update Item Title
            setItemTitle(getItemTitle());
            // Update Item Link
            setItemLink(getItemLink());
            // Update Item Publication Date
            setItemDate(getItemDate());
            // Update Item Description
            setItemDescription(getItemDescription());
            // Update Author
            setAuthor(getAuthor());
            // Update cursor locations
            setCarets();
        }
    }
    
    /**
     * Listener for the trending words JLIST. If no sort is active, then the list
     * is sorted by title by default, to ensure that the default list is never 
     * manipulated. Calls displayTrendingItems to show appropriate RSSFeedItems
     * and set the appropriate GUI components.
     * @see RSSTrendingTopicsFrame#displayTrendingItems(java.lang.String)
     */
    class TrendListen implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            if(listSorted == false)
            {
                radioItemTitle.doClick();
                listSorted = true;
            }
            displayTrendingItems(getTrendingWord());
            indexed = true;
            setIndex();
        }
        
    }
    
    /**
     * Listener for all radio buttons. Sets the active list to sorted, and sorts
     * this list based on whichever radio button fires the event. Sets status,
     * colours and booleans to appropriate values. If the list is currently trending, 
     * then the sorted list is sorted as normal before displayTrending is called again.
     * @see RSSTrendingTopicsFrame#displayTrendingItems(java.lang.String) 
     */
    class RadioListen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            returnColoursToDefault();
            
            // Removes the listener from the list to avoid the nullPointerException
            // and sets boolean to false.
            jListFeedItems.removeListSelectionListener(ls);
            listener = false;
            
            // Removes all contents in the sorted model.
            //            if (!sortModel.isEmpty())
            //                sortModel.removeAllElements();
            
            if (!radioMore.isSelected())
            {
                moreBox.setEnabled(false);
            }
            
            // Calls SORT and passes the related source as the parameter.
            if (ae.getSource() == radioFeedTitle)
            {
                listHandler.sort("feedTitle",false);
                listSorted = true;
                
                jTextFieldFeedTitle.setBackground(sortedColor);
                addToModel("sorted",listHandler.getSorted());
                setSortModelHelper();
                sortedBy = "Feed Title";
                setStatus();
            }
            else if (ae.getSource() == radioItemTitle)
            {
                listHandler.sort("itemTitle",false);
                listSorted = true;
                
                jTextFieldItemTitle.setBackground(sortedColor);
                addToModel("sorted",listHandler.getSorted());
                setSortModelHelper();
                sortedBy = "Item Title";
                setStatus();
            }
            
            else if (ae.getSource() == radioDate)
            {
                listHandler.sort("itemDate",false);
                listSorted = true;
                
                jTextFieldItemDate.setBackground(sortedColor);
                addToModel("sorted",listHandler.getSorted());
                setSortModelHelper();
                sortedBy = "Publication Date";
                setStatus();
                
            }
            else if (ae.getSource() == radioDescription)
            {
                listHandler.sort("itemDescription",false);
                listSorted = true;
                
                jTextAreaItemDescription.setBackground(sortedColor);
                addToModel("sorted",listHandler.getSorted());
                setSortModelHelper();
                sortedBy = "Item Description";
                setStatus();
            }
            else if (ae.getSource() == radioFeedDescription)
            {
                listHandler.sort("feedDesc",false);
                listSorted = true;
                
                jTextFieldFeedDescription.setBackground(sortedColor);
                addToModel("sorted",listHandler.getSorted());
                setSortModelHelper();
                sortedBy = "Feed Description";
                setStatus();
            }
            else if (ae.getSource() == radioMore)
            {
                moreBox.setEnabled(true);
            }
            else if (ae.getSource() == radioDefault)
            {
                listSorted = false;
                setDefault();
            }
            
            // Ensures that the primary JLIST is updated according to any
            // trending word and sorting criteria that may be selected.
            if (listSorted == true && indexed == true)
            {
                displayTrendingItems(getTrendingWord());
            }
            if (ae.getSource() != radioMore)
                setIndex();
        }
    }
    
    /**
     * Listener for all combo box items. Sorts the sorted list based on the item
     * that fires the event. If the list is trending, then the sorted list is sorted
     * as normal before displayTrending is called again.
     * @see RSSTrendingTopicsFrame#displayTrendingItems(java.lang.String) 
     */
    class ComboListen implements ActionListener
    {
        String source = "";
        @Override
        public void actionPerformed(ActionEvent e)
        {
            jListFeedItems.removeListSelectionListener(ls);
            listener = false;
            returnColoursToDefault();
            
            source = moreBox.getSelectedItem().toString();
            switch (source)
            {
                case "Descending Feed Title":
                    listHandler.sort("feedTitle",true);
                    listSorted = true;
                
                    jTextFieldFeedTitle.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Feed Title";
                    setStatus();
                    setIndex();
                    break;
                case "Descending Item Title":
                    listHandler.sort("itemTitle",true);
                    listSorted = true;
                
                    jTextFieldItemTitle.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Item Title";
                    setStatus();
                    setIndex();
                    break;
                case "Descending Item Description":
                    listHandler.sort("itemDescription",true);
                    listSorted = true;
                
                    jTextAreaItemDescription.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Item Description";
                    setStatus();
                    setIndex();
                    break;
                case "Descending Feed Description":
                    listHandler.sort("feedDesc",true);
                    listSorted = true;
                
                    jTextFieldFeedDescription.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Feed Description";
                    setStatus();
                    setIndex();
                    break;
                case "Descending Date":
                    listHandler.sort("itemDate", true);
                    listSorted = true;
                
                    jTextFieldItemDate.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Publication Date";
                    setStatus();
                    setIndex();
                    break;
                case "Descending Author":
                    listHandler.sort("author", true);
                    listSorted = true;
                
                    jTextFieldAuthor.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Reverse Author";
                    setStatus();
                    setIndex();
                    break;
                case "Author":
                    listHandler.sort("author", false);
                    listSorted = true;
                
                    jTextFieldAuthor.setBackground(sortedColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Author";
                    setStatus();
                    setIndex();
                    break;
                case "Randomise":
                    listHandler.sort("randomise",false);
                    listSorted = true;
                
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Random";
                    setStatus();
                    setIndex();
                    break;
                case "Trending Words #":
                    listHandler.sort("trendingWordCount",false);
                    listSorted = true;
                    
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Trending Word Count";
                    setStatus();
                    setIndex();
                    break;
                case "Item URL":
                    listHandler.sort("itemLink",true);
                    listSorted = true;
                
                    jTextFieldItemLink.setBackground(reverseColor);
                    addToModel("sorted",listHandler.getSorted());
                    setSortModelHelper();
                    sortedBy = "Item URL";
                    setStatus();
                    setIndex();
                    break;
                default:
                    System.out.println("BREAK");
                    break;
            }
            if (listSorted == true && indexed == true)
            {
                displayTrendingItems(getTrendingWord());
            }
            setIndex();
        }
    }
    /**
     * Listener for all button components. Calls the appropriate method based
     * on which button fires the event.
     */
    class ButtonListen implements ActionListener
    {
        List<Word> temp;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (listSorted == false)
            {
                radioItemTitle.doClick();
                listSorted = true;
            }
            if (e.getSource() == jButtonTrending)
            {
                jListTrending.removeListSelectionListener(tl);
                if (temp != null)
                {
                    temp.clear();
                }
                
                // Indexes the words in the sorted list.
                indexer.indexWords(listHandler.getSorted());
                temp = indexer.getIndexed();
                
                // Adds all words to the model.
                for (int i = 0; i<temp.size(); i++)
                {
                    trendModel.add(i, temp.get(i));
                }
                
                // Sets the model.
                jListTrending.setModel(trendModel);
                jListTrending.addListSelectionListener(tl);
                indexed = true;
                listSorted = true;
                jListTrending.setSelectedIndex(0);
                jListFeedItems.setSelectedIndex(0);
                setStatus();
                
                // Disable Button
                jButtonTrending.setEnabled(false);
            }
            else if (e.getSource() == jButtonHelp)
            {
                aboutFrame.pack();
                aboutFrame.setTitle("About/Help");
                aboutFrame.setVisible(true);
                aboutFrame.setLocationRelativeTo(null);
                jLabel15.setBackground(sortedColor);
                jLabel15.setOpaque(true);
                jLabel16.setBackground(reverseColor);
                jLabel16.setOpaque(true);
            }
            else if (e.getSource() == aboutClose)
            {
                aboutFrame.setVisible(false);
            }
        }
        
    }
    /**
     * Simple listener to simulate a click. Listener should only be added to the
     * load file text field, to listen for an ENTER key press. If ENTER is pressed,
     * jButtonLoad will simulate being clicked and fire an event to its listener.
     */
    class EnterListen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            jButtonLoad.doClick();}
        }
}


