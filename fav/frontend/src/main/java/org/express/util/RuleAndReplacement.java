package org.express.util;

/**
 * 规则替换对象
 * @author Rei Ayanami
 *
 */
class RuleAndReplacement
{
	private String rule;
	private String replacement;

	public RuleAndReplacement(String rule, String replacement)
	{
		this.rule = rule;
		this.replacement = replacement;
	}

	public String getReplacement()
	{
		return replacement;
	}

	public void setReplacement(String replacement)
	{
		this.replacement = replacement;
	}

	public String getRule()
	{
		return rule;
	}

	public void setRule(String rule)
	{
		this.rule = rule;
	}
}