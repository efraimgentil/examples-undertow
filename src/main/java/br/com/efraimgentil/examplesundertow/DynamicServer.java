package br.com.efraimgentil.examplesundertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.efraimgentil.examplesundertow.util.Page;

public class DynamicServer {

	public final static String VIEW_PACKAGE = "br.com.efraimgentil.examplesundertow.view";

	public static void main(String[] args) throws URISyntaxException {

		ClassLoader classLoader = DynamicServer.class.getClassLoader();
		List<Class<?>> pagesClasses = getPageClasses(VIEW_PACKAGE, classLoader);
		try {
			HttpHandler pathHandler = mountPathHandler(pagesClasses);

			Undertow server = Undertow.builder()
					.addHttpListener(8080, "localhost").setHandler(pathHandler)
					.build();
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static HttpHandler mountPathHandler(List<Class<?>> pagesClasses)
			throws Exception {
		PathHandler pathHandler = Handlers.path();

		for (Class<?> class1 : pagesClasses) {
			Page annotation = class1.getAnnotation(Page.class);
			if (annotation != null) {
				String[] paths = annotation.value();
				Constructor constructor = class1.getConstructor();
				for (String path : paths) {
					pathHandler.addPrefixPath(path,
							(HttpHandler) constructor.newInstance());
				}

			}
		}

		return pathHandler;
	}

	public static List<Class<?>> getPageClasses(String packageToSearch,
			ClassLoader classLoader) {
		List<Class<?>> pageClasses = new ArrayList<>();
		String[] list = getFileList(packageToSearch);
		try {
			for (String string : list) {
				Class<?> loadedClass = classLoader.loadClass(packageToSearch
						+ "." + string.replace(".class", ""));
				pageClasses.add(loadedClass);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pageClasses;
	}

	public static String[] getFileList(String packageToSearch) {
		URL systemResource = DynamicServer.class.getResource("/"
				+ packageToSearch.replaceAll("\\.", "/"));
		File file = new File(systemResource.getPath());
		return file.list();
	}

}
