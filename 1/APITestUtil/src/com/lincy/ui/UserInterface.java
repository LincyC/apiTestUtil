package com.lincy.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EtchedBorder;

import com.lincy.entity.ResultEntity;
import com.lincy.enumeration.RequestMethodEnum;
import com.lincy.service.RequestService;
import com.lincy.service.impl.RequestServiceImpl;

/**
 * @author lcy79
 * 用户界面
 */
public class UserInterface {
	
	private RequestService requestService = new RequestServiceImpl(); 
	/**
	 * 	启动界面
	 */
	public void start() {
		
		// 创建 JFrame 实例
        JFrame frame = new JFrame("http请求测试工具");
        // Setting the width and height of frame
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //用户输入信息panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0,0,450,400);
        inputPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        //输出结果panel
        JPanel outputPanel = new JPanel();
        outputPanel.setBounds(450, 0, 450, 400);
        outputPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        //提交按钮
        JButton submit = new JButton("请求 > ");
        submit.setBounds(400, 200, 100, 25);
        frame.add(submit);
        
        //添加panel到frame中
        frame.add(inputPanel);
        frame.add(outputPanel);
        
        //初始化
        initInputPanel(inputPanel);
        initOutputPanel(outputPanel);
        initSubmit(submit, inputPanel, outputPanel);
        frame.setVisible(true);
	}
	
	/**
	 * 初始化提交按钮
	 * @param buttom
	 */
	private void initSubmit(JButton buttom , JPanel inputPanel, JPanel outputPanel) {
		
		buttom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * 获取数据
				 */
				String url = "";
				String method = "";
				String times ="";
				String requestBody = "";
				
				if(inputPanel.findComponentAt(130,70)!=null)
					url = ((JTextField)(inputPanel.getComponentAt(130,70))).getText().trim();
				if(inputPanel.findComponentAt(130,100)!=null)
					method = ((JComboBox)(inputPanel.getComponentAt(130,100))).getSelectedItem().toString().trim();
				if(inputPanel.findComponentAt(130,130)!=null)
					times = ((JTextField)(inputPanel.getComponentAt(130,130))).getText().trim();
				if(inputPanel.findComponentAt(130,160)!=null)
					for(Component c : ((JScrollPane)(inputPanel.getComponentAt(130,160))).getComponents()){
						if(c instanceof JViewport){
							for(Component co : ((JViewport)c).getComponents())
								if(co instanceof JTextArea)
									requestBody = ((JTextArea)co).getText().trim();
						}
					}
				
				
				ResultEntity res = requestService.request(url, method, requestBody, times);
				
				if(outputPanel.findComponentAt(580,100)!=null)
					for(Component c : ((JScrollPane)(outputPanel.getComponentAt(580,100))).getComponents()){
						if(c instanceof JViewport){
							for(Component co : ((JViewport)c).getComponents())
								if(co instanceof JTextArea)
								((JTextArea)co).setText(res.getResult());
						}
					}
				
				if(outputPanel.findComponentAt(580,70)!=null)
					((JLabel)(outputPanel.getComponentAt(580,70))).setText(res.getDuringTime());
			}
		});
	}

	/**
	 * 初始化用户输入信息panel
	 * @param inputPanel
	 */
	private void initInputPanel(JPanel panel){
		panel.setLayout(null);
		
		JLabel title = new JLabel("输入");
		title.setBounds(20, 10, 100, 50);
		title.setFont(new Font(null, 0, 30));
		panel.add(title);
		
		/**
		 * url label
		 */
		JLabel urlLabel = new JLabel("url: ");
		urlLabel.setBounds(20,70,100,25);
		panel.add(urlLabel);
		
		/**
         * 创建文本域用于用户输入 url
         */
        JTextField urlText = new JTextField(20);
        urlText.setBounds(130,70,200,25);
        panel.add(urlText);
		
        /**
		 * 请求类型 label
		 */
		JLabel requestMethodLabel = new JLabel("requestMethod: ");
		requestMethodLabel.setBounds(20,100,100,25);
		panel.add(requestMethodLabel);
        
		/**
		 * 请求类型选择下拉框
		 */
        JComboBox requestMethodSelect = new JComboBox();
        requestMethodSelect.setBounds(130,100,100,25);
        for (RequestMethodEnum e : RequestMethodEnum.values()) {
        	requestMethodSelect.addItem(e.toString());  
		}
        panel.add(requestMethodSelect);
        
        /**
		 * 请求次数Label
		 */
		JLabel timesLabel = new JLabel("times: ");
		timesLabel.setBounds(20,130,100,25);
		panel.add(timesLabel);
        
		/**
         * 创建文本域用于用户输入 请求次数
         */
        JTextField timesText = new JTextField(20);
        timesText.setBounds(130,130,200,25);
        panel.add(timesText);
		
		/**
		 * requestbody label
		 */
		JLabel requestBodyLabel = new JLabel("requestBody: ");
		requestBodyLabel.setBounds(20,160,100,25);
		panel.add(requestBodyLabel);
		
        /**
         *  输入requestbody的文本域
         */
        JTextArea requestBodyText = new JTextArea();
        JScrollPane jsp = new JScrollPane(requestBodyText);
        jsp.setBounds(130,160,200,200);
        panel.add(jsp);
	}
	
	/**
	 * 输出结果panel
	 * @param outputPanel
	 */
	private void initOutputPanel(JPanel panel){
		panel.setLayout(null);
		
		/**
		 * 返回结果
		 */
		JLabel title = new JLabel("返回");
		title.setBounds(470, 10, 100, 50);
		title.setFont(new Font(null, 0, 30));
		panel.add(title);
		
		/**
		 * 持续时间
		 */
		JLabel durationLabel = new JLabel("duration(ms):");
		durationLabel.setBounds(470,70,100,25);
		panel.add(durationLabel);
		
		/**
		 * 持续时间
		 */
		JLabel duration = new JLabel("");
		duration.setBounds(580,70,80,25);
		panel.add(duration);
		
		/**
		 * responseBodyLabel
		 */
		JLabel responseBodyLabel = new JLabel("responseBody:");
		responseBodyLabel.setBounds(470,100,100,25);
		panel.add(responseBodyLabel);
		
		/**
         *  输入responseBody的文本域
         */
        JTextArea responseBodyText = new JTextArea();
        JScrollPane jsp = new JScrollPane(responseBodyText);
        jsp.setBounds(580,100,300,200);
        panel.add(jsp);
	}
}
