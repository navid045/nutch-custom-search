package ir.co.bayan.simorq.zal.extractor.model;

import ir.co.bayan.simorq.zal.extractor.evaluation.EvaluationContext;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang3.Validate;

/**
 * Trancates a string if its size is greater than max.
 * 
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
public class Truncate extends Function {

	@XmlAttribute
	private int max;

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> extract(Object root, EvaluationContext context) throws Exception {
		Validate.isTrue(args != null && args.size() == 1, "Only one inner function is expected.");
		List<String> res = (List<String>) args.get(0).extract(root, context);
		for (int i = 0; i < res.size(); i++) {
			String item = res.get(i);
			if (item != null && item.length() > max)
				item = item.substring(0, max);
			res.set(i, item);
		}
		return res;
	}

	@Override
	public String toString() {
		return "Truncate [max=" + max + "]";
	}

}