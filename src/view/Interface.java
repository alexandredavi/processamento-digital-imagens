package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import linearizacao.AlgoritmoLinearizacao;
import negativa.AlgoritmoNegativa;
import pdi.CalculadorDePixel;
import ruido.AlgoritmoRuido;
import ruido.ProcessadorMedia2x2;
import ruido.ProcessadorMedia2x2Diagonal;
import ruido.ProcessadorMedia3x3;
import ruido.ProcessadorMediana2x2;
import ruido.ProcessadorMediana2x2Diagonal;
import ruido.ProcessadorMediana3x3;
import tonsdecinza.AlgoritmoTonsDeCinza;
import tonsdecinza.TonsDeCinzaPonderado;
import tonsdecinza.TonsDeCinzaSimples;

public class Interface extends Shell {

    private String diretorioImagem1 = "";
    private String diretorioImagem2 = "";
    private String diretorioImagem3 = "";
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
    private org.eclipse.swt.widgets.List listCores;
    private List<RGB> rgbList = new ArrayList<RGB>();
    private Composite compositeCorLista;
    private Button btnAplicarMedia;
    private Button btn3x3;
    private Button btnxdiagonal;
    private Button btnx;
    private TabItem tbtmTonsDeCinza;
    private Composite composite_1;
    private Button btnCinzaSimples;
    private Button btnCinzaPonderado;
    private Text textPorcentagemR;
    private Text textPorcentagemG;
    private Text textPorcentagemB;
    private TabItem tbtmLinearizao;
    private Composite composite_2;
    private Slider sliderPontoDeCorte;
    private TabItem tbtmNegativa;
    private Composite composite_3;
    private Button btnNegativar;

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
        tabFolder.setBounds(0, 10, 803, 116);

        TabItem tbtmRuido = new TabItem(tabFolder, SWT.NONE);
        tbtmRuido.setText("Ruido");

        Composite composite = new Composite(tabFolder, SWT.NONE);
        tbtmRuido.setControl(composite);
        
        Button btnAplicarMediana = new Button(composite, SWT.NONE);
        btnAplicarMediana.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
        		AlgoritmoRuido process = new ProcessadorMediana3x3();
        		if (btnx.getSelection()) {
        			process = new ProcessadorMediana2x2();
				}else if (btnxdiagonal.getSelection()){
					process = new ProcessadorMediana2x2Diagonal();
				}
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "ruido_mediana");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnAplicarMediana.setBounds(10, 10, 110, 25);
        btnAplicarMediana.setText("Aplicar Mediana");
        
        btnAplicarMedia = new Button(composite, SWT.NONE);
        btnAplicarMedia.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
        		AlgoritmoRuido process = new ProcessadorMedia3x3();
        		if (btnx.getSelection()) {
        			process = new ProcessadorMedia2x2();
				}else if (btnxdiagonal.getSelection()){
					process = new ProcessadorMedia2x2Diagonal();
				}
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "ruido_media");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnAplicarMedia.setText("Aplicar Media");
        btnAplicarMedia.setBounds(10, 41, 110, 25);
        
        btn3x3 = new Button(composite, SWT.RADIO);
        btn3x3.setSelection(true);
        btn3x3.setBounds(151, 37, 52, 16);
        btn3x3.setText("3x3");
        
        btnxdiagonal = new Button(composite, SWT.RADIO);
        btnxdiagonal.setText("2x2Diagonal");
        btnxdiagonal.setBounds(209, 37, 96, 16);
        
        btnx = new Button(composite, SWT.RADIO);
        btnx.setText("2x2");
        btnx.setBounds(314, 37, 96, 16);
        
        tbtmTonsDeCinza = new TabItem(tabFolder, SWT.NONE);
        tbtmTonsDeCinza.setText("Tons de Cinza");
        
        composite_1 = new Composite(tabFolder, SWT.NONE);
        tbtmTonsDeCinza.setControl(composite_1);
        composite_1.setLayout(null);
        
        btnCinzaSimples = new Button(composite_1, SWT.NONE);
        btnCinzaSimples.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
        		AlgoritmoTonsDeCinza process = new TonsDeCinzaSimples();
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "cinza_simples");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnCinzaSimples.setBounds(10, 10, 109, 25);
        btnCinzaSimples.setText("Cinza Simples");
        
        btnCinzaPonderado = new Button(composite_1, SWT.NONE);
        btnCinzaPonderado.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
        		int percentualR = Integer.parseInt(textPorcentagemR.getText());
				int percentualG = Integer.parseInt(textPorcentagemG.getText());
				int percentualB = Integer.parseInt(textPorcentagemB.getText());
				AlgoritmoTonsDeCinza process = new TonsDeCinzaPonderado(percentualR, percentualG, percentualB);
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "cinza_ponderado");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnCinzaPonderado.setBounds(10, 41, 109, 25);
        btnCinzaPonderado.setText("Cinza Ponderado");
        
        Label lblr = new Label(composite_1, SWT.NONE);
        lblr.setBounds(125, 46, 24, 15);
        lblr.setText("%R");
        
        textPorcentagemR = new Text(composite_1, SWT.BORDER);
        textPorcentagemR.setBounds(149, 41, 44, 21);
        
        Label lblg = new Label(composite_1, SWT.NONE);
        lblg.setBounds(199, 46, 24, 15);
        lblg.setText("%G");
        
        textPorcentagemG = new Text(composite_1, SWT.BORDER);
        textPorcentagemG.setBounds(224, 41, 44, 21);
        
        Label lblb = new Label(composite_1, SWT.NONE);
        lblb.setText("%B");
        lblb.setBounds(274, 46, 24, 15);
        
        textPorcentagemB = new Text(composite_1, SWT.BORDER);
        textPorcentagemB.setBounds(299, 41, 44, 21);
        
        tbtmLinearizao = new TabItem(tabFolder, SWT.NONE);
        tbtmLinearizao.setText("Lineariza\u00E7\u00E3o");
        
        composite_2 = new Composite(tabFolder, SWT.NONE);
        tbtmLinearizao.setControl(composite_2);
        composite_2.setLayout(null);
        
        sliderPontoDeCorte = new Slider(composite_2, SWT.NONE);
        sliderPontoDeCorte.addControlListener(new ControlAdapter() {
        	@Override
        	public void controlMoved(ControlEvent arg0) {
        	}
        });
        sliderPontoDeCorte.addDragDetectListener(new DragDetectListener() {
        	public void dragDetected(DragDetectEvent arg0) {
        		int pontoDeCorte = sliderPontoDeCorte.getSelection();
				AlgoritmoLinearizacao process = new AlgoritmoLinearizacao(pontoDeCorte);
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "linearicao");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        sliderPontoDeCorte.setBounds(10, 61, 339, 17);
        sliderPontoDeCorte.setMinimum(0);
        sliderPontoDeCorte.setMaximum(265);
        
        tbtmNegativa = new TabItem(tabFolder, SWT.NONE);
        tbtmNegativa.setText("Negativa");
        
        composite_3 = new Composite(tabFolder, SWT.NONE);
        tbtmNegativa.setControl(composite_3);
        composite_3.setLayout(null);
        
        btnNegativar = new Button(composite_3, SWT.NONE);
        btnNegativar.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent arg0) {
				AlgoritmoNegativa process = new AlgoritmoNegativa();
        		try {
					BufferedImage imagemProcessada = process.processaAlgoritmo(ImageIO.read(new File(diretorioImagem1)));
					salvaImagemProcessada(imagemProcessada, "negativa");
				    abreImagem(3);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnNegativar.setBounds(10, 10, 75, 25);
        btnNegativar.setText("Negativar");
        
        TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
        tabItem.setText("Histograma");
        
        Composite composite_4 = new Composite(tabFolder, SWT.NONE);
        tabItem.setControl(composite_4);
        composite_4.setLayout(null);
        
        Button btnGerarGrafico = new Button(composite_4, SWT.NONE);
        btnGerarGrafico.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    XYDataset dataset = createDataset(ImageIO.read(new File(diretorioImagem1)));
                    final JFreeChart chart = createChart(dataset);
                    BufferedImage grafico = chart.createBufferedImage(1000, 500);
                    salvaImagemProcessada(grafico, "grafico");
                    abreImagem(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnGerarGrafico.setBounds(10, 10, 81, 25);
        btnGerarGrafico.setText("Gerar Grafico");

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
        labelImagem1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 1);
                rgbList.add(rgb);
                listCores.add("R:"+rgb.red+", G:"+rgb.green+", B:"+rgb.blue);
            }
        });
        labelImagem1.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 1);
                preencheCamposRGBeComposite(rgb);
            }
        });
        compositeImagem1.setContent(labelImagem1);
        labelImagem1.setBounds(0, 0, 61, 21);

        compositeImagem2 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        compositeImagem2.setLocation(331, 208);
        compositeImagem2.setSize(293, 347);

        labelImagem2 = new CLabel(compositeImagem2, SWT.NONE);
        labelImagem2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 2);
                rgbList.add(rgb);
                listCores.add("R:"+rgb.red+", G:"+rgb.green+", B:"+rgb.blue);
            }
        });
        labelImagem2.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 2);
                preencheCamposRGBeComposite(rgb);
            }
        });
        compositeImagem2.setContent(labelImagem2);
        labelImagem2.setBounds(0, 0, 61, 21);

        compositeImagem3 = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        compositeImagem3.setLocation(630, 208);
        compositeImagem3.setSize(321, 347);

        labelImagem3 = new CLabel(compositeImagem3, SWT.NONE);
        labelImagem3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDoubleClick(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 3);
                rgbList.add(rgb);
                listCores.add("R:"+rgb.red+", G:"+rgb.green+", B:"+rgb.blue);
            }
        });
        labelImagem3.addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent arg0) {
                RGB rgb = encontraPixel(arg0.x, arg0.y, 3);
                preencheCamposRGBeComposite(rgb);
            }
        });
        compositeImagem3.setContent(labelImagem3);
        labelImagem3.setBounds(0, 0, 61, 21);

        compositeCor = new Composite(this, SWT.BORDER);
        compositeCor.setBounds(422, 131, 88, 45);
        compositeCor.setLayout(null);

        labelPosicaoCor = new Label(compositeCor, SWT.NONE);
        labelPosicaoCor.setBounds(16, 14, 55, 15);

        labelR = new Label(this, SWT.NONE);
        labelR.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
        labelR.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
        labelR.setBounds(13, 132, 72, 39);

        labelG = new Label(this, SWT.NONE);
        labelG.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
        labelG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
        labelG.setBounds(112, 132, 72, 39);

        labelB = new Label(this, SWT.NONE);
        labelB.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
        labelB.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
        labelB.setBounds(218, 132, 72, 39);

        listCores = new org.eclipse.swt.widgets.List(this, SWT.BORDER);
        listCores.setBounds(809, 31, 141, 145);

        listCores.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent event) {
                int rgbSelecionado = listCores.getSelectionIndex();
                RGB rgb = rgbList.get(rgbSelecionado);
                compositeCorLista.setBackground(new Color(null, rgb));
            }

            public void widgetDefaultSelected(SelectionEvent event) {}
        });

        compositeCorLista = new Composite(this, SWT.BORDER);
        compositeCorLista.setBounds(809, 4, 142, 25);
        
        Button btnLimpar = new Button(this, SWT.NONE);
        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                listCores.removeAll();
                rgbList.clear();
            }
        });
        btnLimpar.setBounds(809, 177, 142, 25);
        btnLimpar.setText("Limpar");
        createContents();
    }

    protected JFreeChart createChart(XYDataset dataset) {
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Quantidade de pixels",
            "Pixel",                      
            "Quantidade",                  
            dataset,                
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        chart.setBackgroundPaint(java.awt.Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(java.awt.Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(java.awt.Color.white);
        plot.setRangeGridlinePaint(java.awt.Color.white);
        
        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
    }

    protected XYDataset createDataset(BufferedImage img) {
        CalculadorDePixel calculadorDePixel = new CalculadorDePixel();
        calculadorDePixel.calculaQuantidadeDePixels(img);
        
        int[] qtdPixelsR = calculadorDePixel.getQtdPixelsR();
        int[] qtdPixelsG = calculadorDePixel.getQtdPixelsG();
        int[] qtdPixelsB = calculadorDePixel.getQtdPixelsB();
        
        final XYSeries linhaR = new XYSeries("R");
        final XYSeries linhaG = new XYSeries("G");
        final XYSeries linhaB = new XYSeries("B");

        for (int i = 0; i < qtdPixelsR.length; i++) {
        	linhaR.add(i, qtdPixelsR[i]);
        	linhaG.add(i, qtdPixelsG[i]);
        	linhaB.add(i, qtdPixelsB[i]);
		}

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(linhaR);
        dataset.addSeries(linhaB);
        dataset.addSeries(linhaG);
                
        return dataset;
        
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
        } else if (numeroImagem == 3) {
            if (diretorioImagem3 != null && !diretorioImagem3.equals("")) {
                imagem3 = new Image(null, diretorioImagem3);
                carregaImagem(labelImagem3, imagem3);
            }
        }
    }

    private void carregaImagem(CLabel label, Image imagem) {
        label.setBackground(imagem);
        label.setBounds(label.getBounds().x, label.getBounds().y, imagem.getImageData().width, imagem.getImageData().height);

    }

    private RGB encontraPixel(int x, int y, int indice) {

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
            return rgb;
        }
        
        return null;
    }

    
    private void preencheCamposRGBeComposite(RGB rgb) {
        if (rgb != null) {
            labelR.setText("R:  " + rgb.red);
            labelG.setText("G:  " + rgb.green);
            labelB.setText("B:  " + rgb.blue);
            compositeCor.setBackground(new Color(null, rgb));
        }
    }
    
    @Override
    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

	private void salvaImagemProcessada(BufferedImage imagemProcessada, String nomeImagem) throws IOException {
		File outputfile = new File(System.getProperty("user.home")+"/Desktop/"+nomeImagem+".png");
		ImageIO.write(imagemProcessada, "png", outputfile);
		diretorioImagem3 = outputfile.getPath();
	}
}
