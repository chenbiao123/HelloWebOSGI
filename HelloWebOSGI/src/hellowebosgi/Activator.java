package hellowebosgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

public class Activator implements BundleActivator {

	private ServiceReference serviceReference;
	private HttpService httpservice;
	private static BundleContext bc;

	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");
		bc=context;
		registerResource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
		unregisterResource();
		
	}

	private void registerResource() {

		try {
			serviceReference = bc.getServiceReference(HttpService.class.getName());
			if (serviceReference != null) {
				httpservice = (HttpService) bc.getService(serviceReference);
				httpservice.registerResources("/demo", "page", null);
				System.out.println("注册服务");
			}
		} catch (NamespaceException e) {
			// TODO Auto-generated catch block
			System.out.println(serviceReference);
		}
	}
	
	private void unregisterResource (){
		try {
			httpservice.unregister("/demo");
			System.out.println("删除服务");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
