package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseEvent;

public class Interface extends Shell {

	private String diretorioImagem1 = "";
	private String diretorioImagem2 = "";
	Image imagem1;
	Image imagem2;
	Image imagem3;
	Shell s;
	private ScrolledComposite compositeImagem1;
	private ScrolledComposite compositeImagem2;
	private ScrolledComposite compositeImagem3;
	private CLabel labelImagem1;
	private CLabel labelImagem2;
	private CLabel labelImagem3;
	private Composite compositeCor;
	private Label labelPosicaoCor;
	private Label labelB;
	private Label labelG;
	private Label labelR;

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Interface shell = new Interface(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Interface(Display display) {
		super(display, SWT.SHELL_TRIM);
		s = new Shell(display);
		setLayout(null);

		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBounds(0, 10, 960, 116);

		TabItem tbtmRuido = new TabItem(tabFolder, SWT.NONE);
		tbtmRuido.setText("Ruido");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmRuido.setControl(composite);

		Button btnImagem1 = new Button(this, SWT.NONE);
		btnImagem1.setBounds(10, 177, 75, 25);
		btnImagem1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				diretorioImagem1 = selecinarDiretorio();
				abreImagem(1);
			}
		});
		btnImagem1.setText("Imagem 1");

		Button btnImagem2 = new Button(this, SWT.NONE);
		btnImagem2.setBounds(346, 177, 75, 25);
		btnImagem2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				diretorioImagem2 = selecinarDiretorio();
				abreImagem(2);
			}
		});
		btnImagem2.setText("Imagem 2");

		compositeImagem1 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		compositeImagem1.setLocation(13, 208);
		compositeImagem1.setSize(312, 347);

		labelImagem1 = new CLabel(compositeImagem1, SWT.NONE);
		labelImagem1.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				encontraPixel(arg0.x, arg0.y, 1);
			}
		});
		compositeImagem1.setContent(labelImagem1);
		labelImagem1.setBounds(0, 0, 61, 21);

		compositeImagem2 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		compositeImagem2.setLocation(331, 208);
		compositeImagem2.setSize(293, 347);

		labelImagem2 = new CLabel(compositeImagem2, SWT.NONE);
		labelImagem2.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				encontraPixel(arg0.x, arg0.y, 2);
			}
		});
		compositeImagem2.setContent(labelImagem2);
		labelImagem2.setBounds(0, 0, 61, 21);

		compositeImagem3 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		compositeImagem3.setLocation(630, 208);
		compositeImagem3.setSize(321, 347);

		labelImagem3 = new CLabel(compositeImagem3, SWT.NONE);
		labelImagem3.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				encontraPixel(arg0.x, arg0.y, 3);
			}
		});
		compositeImagem3.setContent(labelImagem3);
		labelImagem3.setBounds(0, 0, 61, 21);

		compositeCor = new Composite(this, SWT.NONE);
		compositeCor.setBounds(422, 131, 88, 45);
		compositeCor.setLayout(null);

		labelPosicaoCor = new Label(compositeCor, SWT.NONE);
		labelPosicaoCor.setBounds(16, 14, 55, 15);

		labelR = new Label(this, SWT.NONE);
		labelR.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		labelR.setBounds(13, 132, 72, 39);

		labelG = new Label(this, SWT.NONE);
		labelG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		labelG.setBounds(112, 132, 72, 39);

		labelB = new Label(this, SWT.NONE);
		labelB.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		labelB.setBounds(218, 132, 72, 39);
		createContents();
	}

	protected void createContents() {
		setText("SWT Application");
		setSize(989, 603);

	}

	private String selecinarDiretorio() {
		FileDialog fd = new FileDialog(s, SWT.OPEN);
		fd.setText("Abrir");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.*", "*.png", "*.bmp", "*.jpg" };
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		if (selected != null) {
			return selected;
		}
		return "";
	}

	public void abreImagem(int numeroImagem) {
		if (numeroImagem == 1) {
			if (diretorioImagem1 != null && !diretorioImagem1.equals("")) {
				imagem1 = new Image(null, diretorioImagem1);
				carregaImagem(labelImagem1, imagem1);
			}
		} else if (numeroImagem == 2) {
			if (diretorioImagem2 != null && !diretorioImagem2.equals("")) {
				imagem2 = new Image(null, diretorioImagem2);
				carregaImagem(labelImagem2, imagem2);
			}
		}
	}

	private void carregaImagem(CLabel label, Image imagem) {
		label.setBackground(imagem);
		label.setBounds(label.getBounds().x, label.getBounds().y, imagem.getImageData().width,
				imagem.getImageData().height);

	}

	private void encontraPixel(int x, int y, int indice) {

		labelPosicaoCor.setText(x + "," + y);

		ImageData imageData = null;
		PaletteData paletteData = null;

		if (indice == 1 && imagem1 != null) {
			imageData = imagem1.getImageData();
			paletteData = imageData.palette;
		} else if (indice == 2 && imagem2 != null) {
			imageData = imagem2.getImageData();
			paletteData = imageData.palette;
		} else if (indice == 3 && imagem3 != null) {
			imageData = imagem3.getImageData();
			paletteData = imageData.palette;
		}
		
		if (paletteData != null && x > -1 && y > -1) {
			int pixel = imageData.getPixel(x, y);
			RGB rgb = paletteData.getRGB(pixel);
			labelR.setText("R:  "+rgb.red);
			labelG.setText("G:  "+rgb.green);
			labelB.setText("B:  "+rgb.blue);
			compositeCor.setBackground(new Color(null, rgb));
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
