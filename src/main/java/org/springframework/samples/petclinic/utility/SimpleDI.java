package org.springframework.samples.petclinic.utility;

import org.springframework.samples.petclinic.owner.PetRepository;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * this simple class shows the main idea behind a Dependency Injection library
 */
public class SimpleDI {

	private static SimpleDI simpleDI;
	private HashMap<Class<?>, Object> container;

	private SimpleDI() {
		container = new HashMap<>();
	}

	public static SimpleDI getDIContainer() throws Exception {
		if (simpleDI == null) {
			simpleDI = new SimpleDI();
		}
		return simpleDI;
	}

	public void provideByInstance(Class<?> typeClass, Object instanceOfType) {
		container.put(typeClass, instanceOfType);
	}

	public void provideByAConstructorFunction(Class<?> typeClass, Callable<Object> providerFunction) throws Exception {
		container.put(typeClass, providerFunction.call());
	}

	public Object getInstanceOf(Class<?> requiredType) throws Exception {
		return container.get(requiredType);
	}
}
