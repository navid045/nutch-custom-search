package ir.co.bayan.simorq.zal.nutch.extractor.engine;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Taha Ghasemi <taha.ghasemi@gmail.com>
 * 
 */
public class CssEngine extends ExtractEngine<CssContext> {

	@Override
	protected CssContext createContext(String url, byte[] content, String encoding, String contentType)
			throws Exception {
		org.jsoup.nodes.Document parsedDoc = Jsoup.parse(new String(content, encoding));
		return new CssContext(this, url, parsedDoc);
	}

	@Override
	public List<?> evaluate(String value, CssContext context) throws Exception {
		return context.getRoot().select(value);
	}

	@Override
	public List<?> getAttribute(List<?> res, String name, CssContext context) throws Exception {
		if (res instanceof Elements) {
			Elements elements = (Elements) res;
			List<String> attrs = new ArrayList<>(elements.size());
			for (Element element : elements) {
				attrs.add(element.attr(name));
			}
			return attrs;
		}
		return null;

	}

	@Override
	public List<?> getText(List<?> res, CssContext context) throws Exception {
		if (res instanceof Elements) {
			Elements elements = (Elements) res;
			List<String> texts = new ArrayList<>(elements.size());
			for (Element element : elements) {
				texts.add(element.text());
			}
			return texts;
		}
		return null;
	}
}