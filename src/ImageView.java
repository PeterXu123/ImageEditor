import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import images.ImageUtilities;

/**
 * It represents the GUI view of imageData and it is the View in MVC pattern. It includes every
 * element in the GUI view.
 */
public class ImageView extends JFrame implements ViewInterface {
  // intX, intY, endX, endY are used for mouse drag event.
  private int initX;
  private int initY;
  private int endX;
  private int endY;
  // imagePicture represents the image.
  private final JLabel imagePicture;
  // buttons below are buttons on the GUI view.
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton greyScaleButton;
  private final JButton sepiaButton;
  private final JButton highlightEdgeButton;
  private final JButton ditherButton;
  private final JButton mosaicsButton;
  private final JButton equalizationButton;
  private final JButton redEyeRemovalButton;
  private final JButton selectScript;
  private final JButton runScriptButton;
  // JMenuItems below are in the menu of the GUI view.
  private final JMenuItem rainbowItem;
  private final JMenuItem rainbowItem1;
  private final JMenuItem checkerboardItem;
  private final JMenuItem load;
  private final JMenuItem store;
  private final JMenuItem franceFlag;
  private final JMenuItem norwayFlag;
  private final JMenuItem switzerlandFlag;
  private final JMenuItem greeceFlag;
  private final JMenuItem addBorder;
  private final JMenuItem addSticker;
  private final JMenuItem addText;
  private final int ImageWindowHeight = 500;
  private final int ImageWindowWidth = 500;
  private final Map<String, JButton> nameToButton;
  private final Map<String, JMenuItem> nameToMenuItem;
  private JFileChooser j;

  // JScrollPane for the image
  private JScrollPane jp;
  // panel for the image
  private final JPanel westPanel;
  // test area for the script
  private final JTextArea textArea;

  /**
   * It constructs the GUI view with a given name and initialize all fields in the class. For
   * example, add buttons on panel.
   *
   * @param caption the name of the JFrame
   */
  public ImageView(String caption) {
    super(caption);
    nameToMenuItem = new HashMap<>();
    nameToButton = new HashMap<>();
    initX = -1;
    initY = -1;
    endX = -1;
    endY = -1;
    setPreferredSize(new Dimension(1250, 600));
    setLocation(500, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    blurButton = new JButton("blur");
    blurButton.setActionCommand("blur");

    sharpenButton = new JButton("sharpen");
    sharpenButton.setActionCommand("sharpen");

    greyScaleButton = new JButton("greyScale");
    greyScaleButton.setActionCommand("greyScale");

    sepiaButton = new JButton("sepia");
    sepiaButton.setActionCommand("sepia");

    highlightEdgeButton = new JButton("highlightEdge");
    highlightEdgeButton.setActionCommand("highlightEdge");

    ditherButton = new JButton("dithering");
    ditherButton.setActionCommand("dithering");

    mosaicsButton = new JButton("mosaics");
    mosaicsButton.setActionCommand("mosaics");

    equalizationButton = new JButton("equalization");
    equalizationButton.setActionCommand("equalization");
    //    this.add(equalizationButton);

    redEyeRemovalButton = new JButton("remove red eyes");
    redEyeRemovalButton.setActionCommand("remove red eyes");
    //    this.add(redEyeRemovalButton);

    runScriptButton = new JButton("run script from text below ↓");

    selectScript = new JButton("select script");

    nameToButton.put("blur", blurButton);
    nameToButton.put("sharpen", sharpenButton);
    nameToButton.put("grey", greyScaleButton);
    nameToButton.put("sepia", sepiaButton);
    nameToButton.put("highlight", highlightEdgeButton);
    nameToButton.put("dither", ditherButton);
    nameToButton.put("mosaic", mosaicsButton);
    nameToButton.put("equalization", equalizationButton);
    nameToButton.put("redEyesRemove", redEyeRemovalButton);
    nameToButton.put("runScript", runScriptButton);
    nameToButton.put("selectScript", selectScript);

    rainbowItem = new JMenuItem("horizontal rainbow");
    rainbowItem.setActionCommand("horizontal rainbow");

    rainbowItem1 = new JMenuItem("vertical rainbow");
    rainbowItem1.setActionCommand("vertical rainbow");

    //    this.add(rainbowButton);

    checkerboardItem = new JMenuItem("checkerboard");
    checkerboardItem.setActionCommand("checkerboard");
    //    this.add(checkerboardButton);

    load = new JMenuItem("load image");
    load.setActionCommand("load");
    //    this.add(loadButton);

    store = new JMenuItem("save image");
    store.setActionCommand("store");
    //    this.add(storeButton);

    franceFlag = new JMenuItem("France");
    switzerlandFlag = new JMenuItem("Switzerland");
    norwayFlag = new JMenuItem("Norway");
    greeceFlag = new JMenuItem("Greece");

    addBorder = new JMenuItem("Add Border");
    addText = new JMenuItem("Add Text");
    addSticker = new JMenuItem("Add Sticker");
    nameToMenuItem.put("AddBorder", addBorder);
    nameToMenuItem.put("AddSticker", addSticker);
    nameToMenuItem.put("AddText", addText);

    nameToMenuItem.put("FranceFlag", franceFlag);
    nameToMenuItem.put("SwitzerlandFlag", switzerlandFlag);
    nameToMenuItem.put("NorwayFlag", norwayFlag);
    nameToMenuItem.put("GreeceFlag", greeceFlag);
    nameToMenuItem.put("load", load);
    nameToMenuItem.put("store", store);
    nameToMenuItem.put("checkerboard", checkerboardItem);
    nameToMenuItem.put("horizontalRainbow", rainbowItem);
    nameToMenuItem.put("verticalRainbow", rainbowItem1);

    //    runScriptButton.setPreferredSize(new Dimension(500, 20));
    JMenuBar mb = new JMenuBar();
    //    mb.setBackground(Color.RED);
    mb.setOpaque(true);

    JMenu subMenu = new JMenu("Flags");
    subMenu.add(franceFlag);
    subMenu.add(switzerlandFlag);
    subMenu.add(norwayFlag);
    subMenu.add(greeceFlag);

    JMenu functions = new JMenu("Image_Generation");
    JMenu saveOrLoad = new JMenu("Load/Save");
    JMenu enhancement = new JMenu("Enhancement");
    functions.setBackground(Color.blue);
    saveOrLoad.add(load);
    saveOrLoad.add(store);
    functions.add(checkerboardItem);
    functions.add(rainbowItem);
    functions.add(rainbowItem1);
    enhancement.add(addBorder);
    enhancement.add(addSticker);
    enhancement.add(addText);
    functions.add(subMenu);
    mb.add(saveOrLoad);
    mb.add(functions);
    mb.add(enhancement);
    this.setJMenuBar(mb);
    Container mainContainer = this.getContentPane();
    mainContainer.setLayout(new BorderLayout(8, 6));

    JPanel buttonModifyPanel = new JPanel();
    buttonModifyPanel.setBorder(new LineBorder(Color.blue));
    buttonModifyPanel.setLayout(new GridLayout(1, 9, 5, 5));

    JPanel midPanel = new JPanel();
    midPanel.setLayout(new BorderLayout());
    westPanel = new JPanel();
    westPanel.setBackground(Color.PINK);
    midPanel.setBorder(new LineBorder(Color.CYAN));
    JPanel generateGrid = new JPanel();
    generateGrid.setLayout(new GridLayout(4, 3, 10, 5));
    generateGrid.add(blurButton);
    generateGrid.add(sharpenButton);
    generateGrid.add(sepiaButton);
    generateGrid.add(highlightEdgeButton);
    generateGrid.add(greyScaleButton);
    generateGrid.add(mosaicsButton);
    generateGrid.add(ditherButton);
    generateGrid.add(equalizationButton);
    generateGrid.add(redEyeRemovalButton);
    generateGrid.add(selectScript);
    generateGrid.add(runScriptButton);
    midPanel.add(generateGrid, BorderLayout.NORTH);
    textArea = new JTextArea(10, 10);
    JScrollPane sp = new JScrollPane(textArea);
    //    midPanel.add(new JLabel("text script"), BorderLayout.CENTER);
    midPanel.add(sp, BorderLayout.CENTER);

    BufferedImage image = ImageUtilities.toBufferImage(new int[1200][1200][3]);
    imagePicture = new JLabel(new ImageIcon(image));

    jp = new JScrollPane(imagePicture);
    jp.setBorder(new LineBorder(Color.green));
    jp.setPreferredSize(new Dimension(ImageWindowWidth, ImageWindowHeight));
    westPanel.add(jp);
    //    eastPanel.setSize(new Dimension(500, 500));
    westPanel.setBorder(new LineBorder(Color.red));
    //    eastPanel.setPreferredSize(new Dimension(500, 500));
    //    midPanel.add(jp);
    mainContainer.add(midPanel, BorderLayout.EAST);
    mainContainer.add(westPanel);
    this.pack();
    setVisible(true);

  }

  //  @Override
  //  public void resetFocus() {
  //    this.setFocusable(true);
  //    this.requestFocus();
  //  }

  @Override
  public void setFeatures(Features f) throws Exception {
    load.addActionListener(e -> loadContent(f));

    selectScript.addActionListener(e -> this.selectScriptContent(f));

    runScriptButton.addActionListener(e -> runScriptContent(f));

    store.addActionListener(e -> storeContent(f));

    blurButton.addActionListener(l -> f.imageBlur());

    sharpenButton.addActionListener(l -> f.imageSharpening());

    sepiaButton.addActionListener(l -> f.imageSepiaTone());

    ditherButton.addActionListener(l -> f.imageDithering());

    highlightEdgeButton.addActionListener(l -> f.highlightEdgeGrey());

    equalizationButton.addActionListener(l -> f.histogramEqualization());

    greyScaleButton.addActionListener(l -> f.imageGreyScale());

    redEyeRemovalButton.addActionListener(e -> f.removeRedEye(initX, endX, initY, endY));

    mosaicsButton.addActionListener(e -> mosaicsContent(f));

    rainbowItem.addActionListener(e -> rainBowItemContent(f));

    rainbowItem1.addActionListener(e -> rainBowItem1Content(f));

    checkerboardItem.addActionListener(e -> checkerboardItemContent(f));

    greeceFlag.addActionListener(e -> greeceFlagContent(f));

    norwayFlag.addActionListener(e -> norwayFlagContent(f));

    switzerlandFlag.addActionListener(e -> switzerlandFlagConent(f));

    franceFlag.addActionListener(e -> franceFlagContent(f));

    addBorder.addActionListener(e -> f.addBorder());

    addSticker.addActionListener(e -> f.addSticker());

    addText.addActionListener(e -> f.addMeme());

    imagePicture.addMouseListener(
        new MouseListener() {

          /**
           * Nothing.
           * @param e event
           */
          @Override
          public void mouseClicked(MouseEvent e) {
            return;
          }

          @Override
          public void mousePressed(MouseEvent e) {
            initX = e.getX();
            initY = e.getY();
          }

          @Override
          public void mouseReleased(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
          }

          /**
           * Nothing.
           * @param e event
           */
          @Override
          public void mouseEntered(MouseEvent e) {
            return;
          }

          /**
           * Nothing.
           * @param e event
           */
          @Override
          public void mouseExited(MouseEvent e) {
            return;
          }
        });
  }

  @Override
  public void setImage(IViewModel image1) {
    BufferedImage image = image1.getData();
    imagePicture.setIcon(new ImageIcon(image));
    westPanel.removeAll();
    jp = new JScrollPane(imagePicture);
    int newWidth = Math.min(image.getWidth(), ImageWindowWidth);
    int newHeight = Math.min(image.getHeight(), ImageWindowHeight);
    jp.setPreferredSize(new Dimension(newWidth, newHeight));
    westPanel.add(jp);
    this.setVisible(true);
    update();
  }

  /** It updates the view. */
  private void update() {
    this.repaint();
    westPanel.repaint();
  }

  /**
   * Handle error and pop up message for users.
   * @param ex the exception that is thrown
   */
  public void handleError(Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(
        new JFrame(), ex.getMessage() + " operation invalid", "Dialog",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void cleanScript() {
    this.textArea.setText("");
  }

  @Override
  public void showSuccess() {
    JOptionPane.showMessageDialog(new JFrame(), "Done", "Dialog",
            JOptionPane.INFORMATION_MESSAGE);
  }

  protected void clickMenuItem(String commandInfo) {
    if ((nameToMenuItem.containsKey(commandInfo))) {
      nameToMenuItem.get(commandInfo).doClick();
    } else {
      throw new IllegalArgumentException("no such menu item");
    }
  }

  protected void clickButton(String commandInfo) {
    if ((nameToButton.containsKey(commandInfo))) {
      nameToButton.get(commandInfo).doClick();
    } else {
      throw new IllegalArgumentException("no such button");
    }
  }

  /**
   * helper method for load button event.
   *
   * @param f the Features
   */
  private void loadContent(Features f) {
    //    j = new JFileChooser();
    //    j.showSaveDialog(null);
    //    if (j.getSelectedFile() == null) {
    //      return;
    //    }

    f.loadImage();
  }

  /**
   * helper method for select Script button event.
   *
   * @param f the Features
   */
  private void selectScriptContent(Features f) {
    //    j = new JFileChooser();
    //    j.showSaveDialog(null);
    //    if (j.getSelectedFile() == null) {
    //      return;
    //    }

    f.selectScript();
  }

  /**
   * helper method for run Script button event.
   *
   * @param f the Features
   */
  private void runScriptContent(Features f) {
    f.runScript();
  }

  /**
   * helper method for save event.
   *
   * @param f the Features
   */
  private void storeContent(Features f) {
    f.storeImage();
  }

  @Override
  public String userInput(String question) {
    String seed = JOptionPane.showInputDialog(question);
    return seed;
  }

  @Override
  public String userStoreAddress(String question) {
    String fileName = JOptionPane.showInputDialog("Give file name with suffix like .png");
    j = new JFileChooser();

    j.setCurrentDirectory(new File("."));
    j.setDialogTitle("select directory");
    j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    j.showSaveDialog(null);
    if (j.getSelectedFile() == null) {
      return null;
    }
    return j.getSelectedFile().toString() + "\\" + fileName;
  }

  @Override
  public String userInputFromTextArea() {
    return textArea.getText();
  }

  @Override
  public Color userColorChoose() {
    Color userColor = JColorChooser.showDialog(null, "Pick a color", Color.black);
    return userColor;
  }

  @Override
  public Point userClickPoint() {
    return new Point(endX, endY);
  }

  @Override
  public String userChosen() {
    File dir = new File(System.getProperty("user.dir"));
    j = new JFileChooser(dir);
    j.showSaveDialog(null);
    if (j.getSelectedFile() == null) {
      return null;
    }
    return j.getSelectedFile().getAbsolutePath();
  }

  /**
   * helper method for mosaics button event.
   *
   * @param f the Features
   */
  private void mosaicsContent(Features f) {
    System.out.println(123321);
    f.imageMosaicing();
    //              midPanel.add
  }

  /**
   * helper method for horizontal rainbow event.
   *
   * @param f the Features
   */
  private void rainBowItemContent(Features f) {
    f.generateRainbowStrips(true);
  }

  /**
   * helper method for vertical rainbow event.
   *
   * @param f the Features
   */
  private void rainBowItem1Content(Features f) {

    f.generateRainbowStrips(false);
  }

  /**
   * helper method for checkerboard event.
   *
   * @param f the Features
   */
  private void checkerboardItemContent(Features f) {

    f.generateCheckerboard();
  }

  /**
   * helper method for Greece flag event.
   *
   * @param f the Features
   */
  private void greeceFlagContent(Features f) {

    f.generateGreeceFlag();
  }

  /**
   * helper method for Norway flag event.
   *
   * @param f the Features
   */
  private void norwayFlagContent(Features f) {

    f.generateNorwayFlag();
  }

  /**
   * helper method for Switzerland flag event.
   *
   * @param f the Features
   */
  private void switzerlandFlagConent(Features f) {

    f.generateSwitzerlandFlag();
  }

  /**
   * helper method for France flag event.
   *
   * @param f the Features
   */
  private void franceFlagContent(Features f) {

    f.generateFranceFlag();
  }

  private void stickerContent(Features f) {
    f.addSticker();
  }
}
