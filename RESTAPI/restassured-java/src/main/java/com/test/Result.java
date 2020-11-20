package com.test;

public class Result
{
    private String numQuestions;

    private String reward;

    private String entry;

    private String name;

    private String startTime;

    private String endTime;

    private String _id;

    public String getNumQuestions ()
    {
        return numQuestions;
    }

    public void setNumQuestions (String numQuestions)
    {
        this.numQuestions = numQuestions;
    }

    public String getReward ()
    {
        return reward;
    }

    public void setReward (String reward)
    {
        this.reward = reward;
    }

    public String getEntry ()
    {
        return entry;
    }

    public void setEntry (String entry)
    {
        this.entry = entry;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getStartTime ()
    {
        return startTime;
    }

    public void setStartTime (String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime ()
    {
        return endTime;
    }

    public void setEndTime (String endTime)
    {
        this.endTime = endTime;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [numQuestions = "+numQuestions+", reward = "+reward+", entry = "+entry+", name = "+name+", startTime = "+startTime+", endTime = "+endTime+", _id = "+_id+"]";
    }
}
