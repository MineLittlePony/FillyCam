package com.hepolite.fillycam;

import com.mumfrey.liteloader.core.runtime.Obf;
import com.mumfrey.liteloader.util.PrivateFields;

import net.minecraft.client.renderer.EntityRenderer;

public class FillyFields<P, T> extends PrivateFields<P, T>
{

	public static final PrivateFields<EntityRenderer, Float> thirdPersonDistance = create(EntityRenderer.class,
			FObf.thirdPersonDistance);

	protected FillyFields(Class<P> c, Obf obf)
	{
		super(c, obf);
	}

	private static <P, T> PrivateFields<P, T> create(Class<P> c, Obf obf)
	{
		return new FillyFields<P, T>(c, obf);
	}

	private static class FObf extends Obf
	{

		private static final Obf thirdPersonDistance = new FObf("field_78490_B", "q", "thirdPersonDistance");

		protected FObf(String seargeName, String obfName, String mcpName)
		{
			super(seargeName, obfName, mcpName);
			// TODO Auto-generated constructor stub
		}

	}
}
