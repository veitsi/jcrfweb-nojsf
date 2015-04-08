package wbpckg;

// https://github.com/veitsi/jcrf-noweb
import java.util.ArrayList;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jcrf {
	@XmlElement
	Jcrf jcrf;
	@XmlElement
        public int cursor;
        @XmlElement
	public ArrayList<Visit> vst = new ArrayList<Visit>();

	public Jcrf() {
		super();
	}
        public void updAddRow(String vstid, String ptn, String nmr, String dt, String ttr){
           if (Integer.parseInt(ptn)<1 || Integer.parseInt(nmr)<1 ||
                   Float.parseFloat(ttr)<30.0 || Float.parseFloat(ttr)>40.0 ) return;
           int crt=Integer.parseInt(vstid);
           if (crt==0){
               vst.add(new Visit(0,0,0,366));
               crt=vst.size()-1;              
           }
            vst.set(crt-1, new Visit(crt, Integer.parseInt(ptn), Integer.parseInt(nmr),
            Math.round(10*Float.parseFloat(ttr))));
            System.out.println("in updAdd");
            this.toXml();
            
        }

	public static void main(String[] args) {
		Jcrf jcrf = new Jcrf();
		jcrf.addTestData();

		System.out.println(jcrf.vst);

		jcrf.toXml();
		jcrf.fromXml();

		System.out.println(jcrf.vst);
	}

	public void toXml() {
		try {
			File file = new File("jcrf.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Jcrf.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); // output
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(this, file);
			jaxbMarshaller.marshal(this, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void fromXml() {
		try {
			File file = new File("jcrf.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Jcrf.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jcrf = (Jcrf) jaxbUnmarshaller.unmarshal(file);
			vst = jcrf.vst;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void addTestData() {
		vst.add(new Visit(1, 1, 1, 366));
		vst.add(new Visit(2 ,1, 2, 365));
		vst.add(new Visit(3, 2, 1, 364));
                vst.add(new Visit(0, 0, 0, 366));
	}
}